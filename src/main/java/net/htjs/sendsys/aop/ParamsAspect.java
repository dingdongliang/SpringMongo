package net.htjs.sendsys.aop;

import net.htjs.sendsys.model.SysConstant;
import net.htjs.sendsys.mongo.IpAddr;
import net.htjs.sendsys.mongo.UserInfo;
import net.htjs.sendsys.service.IpAddrService;
import net.htjs.sendsys.utils.AESUtil;
import net.htjs.sendsys.utils.AddrUtil;
import net.htjs.sendsys.utils.DateUtil;
import net.htjs.sendsys.utils.IpUtil;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;

/**
 * Description: 处理请求IP与参数，order设定不同AOP之间的执行顺序，越小优先级越高
 * author  dyenigma
 * date 2016/9/18 18:01
 */
@Aspect
@Component
@Order(2)
public class ParamsAspect {

    @Autowired
    private IpAddrService ipAddrService;
    private static final Logger logger = LoggerFactory.getLogger(ParamsAspect.class);

    //获取IP地址对应的其他信息
    private IpAddr getIpAddr(String ip) {
        return ipAddrService.selectIpAddr(AddrUtil.ipToLong(ip));
    }

    //切入点，所有带有SetParams注解的方法或类
    @Pointcut("@annotation(net.htjs.sendsys.annotation.GetParams)")
    public void paramAspect() {
    }

    /**
     * Description: 判断某个参数是否为空，处理没有加密的参数
     * methodName:getParam
     * Time:2016/9/23 10:08
     * param:[param, request]
     * return:java.lang.String
     */
    private String getParam(String param, HttpServletRequest request) {

        return (request.getParameter(param) == null || "null".equals(request.getParameter(param))) ? "" : request
                .getParameter(param);
    }

    /**
     * Description: 获取加密参数信息并解密
     * methodName:getEncryptParam
     * Time:2016/9/23 11:59
     * param:[param, request]
     * return:java.lang.String
     */
    private String getEncryptParam(String param, HttpServletRequest request) {
        String str = "";
        String content = request.getParameter(param);
        try {
            str = (content == null || "null".equals(content)) ? "" :
                    new String(AESUtil.decrypt(AESUtil.parseHexStr2Byte(content)), "UTF-8");
        } catch (InvalidAlgorithmParameterException e) {
            logger.info("加密异常：", e);
        } catch (UnsupportedEncodingException e) {
            logger.info("字符编码转换异常：", e);
        }
        return str;
    }

    /**
     * Description: 首先获取IP地址，判断是否恶意访问，然后解密后，集中处理访问参数
     * methodName:doBefore
     * Time:2016/9/23 10:08
     * param:[]
     * return:void
     */
    @Before("paramAspect()")
    public void doBefore() {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        HttpSession session = request.getSession();

        String keyFlag = getParam("KeyFlag", request);

        //处理参数，解密
        UserInfo userInfo = new UserInfo();
        userInfo.setKeyFlag(keyFlag);

        //加解密的不同情况，参数的处理方式不一样
        if (keyFlag.equals(SysConstant.ENFLAG)) {
            userInfo.setClientType(getEncryptParam("ClientType", request));
            userInfo.setClientVersion(getEncryptParam("ClientVersion", request));
            userInfo.setClientId(getEncryptParam("ClientId", request));
            userInfo.setProject(getEncryptParam("Project", request));
            userInfo.setUserId(getEncryptParam("UserId", request));
            userInfo.setOtherData(getEncryptParam("OtherData", request));
            userInfo.setClientUrl(getEncryptParam("ClientUrl", request));
        } else {
            userInfo.setClientType(getParam("ClientType", request));
            userInfo.setClientVersion(getParam("ClientVersion", request));
            userInfo.setClientId(getParam("ClientId", request));
            userInfo.setProject(getParam("Project", request));
            userInfo.setUserId(getParam("UserId", request));
            userInfo.setOtherData(getParam("OtherData", request));
            userInfo.setClientUrl(getParam("ClientUrl", request));
        }
        userInfo.setCreateTime(DateUtil.dateAndTime());
        String ip = IpUtil.getIpAddr(request);
        userInfo.setRequestIp(ip);
        IpAddr ipAddr = getIpAddr(ip);
        if (ipAddr != null) {
            userInfo.setRequestAddr(ipAddr.getAddr());
            userInfo.setNetCompany(ipAddr.getCompany() == null ? "" : ipAddr.getCompany());
        }
        session.setAttribute("userInfo", userInfo);

    }
}
