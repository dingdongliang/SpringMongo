package net.htjs.sendsys.aop;

import net.htjs.sendsys.annotation.LogRecord;
import net.htjs.sendsys.model.ProLog;
import net.htjs.sendsys.model.SysConstant;
import net.htjs.sendsys.service.LogService;
import net.htjs.sendsys.utils.AESUtil;
import net.htjs.sendsys.utils.DateUtil;
import net.htjs.sendsys.utils.IpUtil;
import org.aspectj.lang.JoinPoint;
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
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.security.InvalidAlgorithmParameterException;


/**
 * Description: 切点类，配置哪里切入，要做什么
 * author  dyenigma
 * date 2016/9/18 16:05
 */
@Aspect
@Component
@Order(3)
public class LogAspect {
    @Autowired
    private LogService logService;

    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    //切入点，所有带有LogRecord注解的方法或类
    @Pointcut("@annotation(net.htjs.sendsys.annotation.LogRecord)")
    public void recordAspect() {
    }

    private String getParam(String param, HttpServletRequest request) {

        return (request.getParameter(param) == null || "null".equals(request.getParameter(param))) ? "" : request
                .getParameter(param);
    }

    /**
     * Description: 前置通知,这里的@Before的内容，要和上面的某个切入点保持一致
     * methodName:doBefore
     * Time:2016/9/23 10:08
     * param:[joinPoint]
     * return:void
     */
    @Before("recordAspect()")
    public void doBefore(JoinPoint joinPoint) {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();

        String keyFlag = getParam("KeyFlag", request);

        ProLog log = new ProLog();
        try {
            log.setDescription(getMethodDescription(joinPoint));
        } catch (Exception e) {
            logger.info("日志前置切面通知异常,异常信息:{}", e);
        }
        log.setMethod(joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()");
        log.setRequestIp(IpUtil.getIpAddr(request));
        log.setErrCode(null);
        log.setErrDetail(null);

        if (keyFlag.equals(SysConstant.ENFLAG)) {
            try {
                log.setCreate(request.getParameter("UserId") == null ? "" : new String(AESUtil.decrypt(
                        AESUtil.parseHexStr2Byte(request.getParameter("UserId"))), "UTF-8"));
            } catch (InvalidAlgorithmParameterException e) {
                logger.info("解密异常：", e);
            } catch (UnsupportedEncodingException e) {
                logger.info("字符编码转换异常：", e);
            }
        } else {
            log.setCreate(request.getParameter("UserId") == null ? "" : request.getParameter("UserId"));
        }
        log.setCreateTime(DateUtil.dateAndTime());
        //保存数据库
        logService.add(log);

    }


    /**
     * Description: 获取注解中对方法的描述信息
     * methodName:getMethodDescription
     * Time:2016/9/23 10:08
     * param:[joinPoint]
     * return:java.lang.String
     */
    private static String getMethodDescription(JoinPoint joinPoint) {

        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = null;
        try {
            targetClass = Class.forName(targetName);
        } catch (ClassNotFoundException e) {
            logger.info("ClassNotFoundException:{}", e);
        }
        Method[] methods = targetClass != null ? targetClass.getMethods() : new Method[0];
        String description = "";
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] objects = method.getParameterTypes();
                if (objects.length == arguments.length) {
                    description = method.getAnnotation(LogRecord.class).description();
                    break;
                }
            }
        }
        return description;
    }
}
