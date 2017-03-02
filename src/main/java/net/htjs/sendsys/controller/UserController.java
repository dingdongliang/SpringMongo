package net.htjs.sendsys.controller;

import com.alibaba.fastjson.JSON;
import net.htjs.sendsys.annotation.GetParams;
import net.htjs.sendsys.annotation.LogRecord;
import net.htjs.sendsys.model.ReturnInfo;
import net.htjs.sendsys.model.SysConstant;
import net.htjs.sendsys.mongo.Msg;
import net.htjs.sendsys.mongo.UserInfo;
import net.htjs.sendsys.service.MsgService;
import net.htjs.sendsys.service.UserInfoService;
import net.htjs.sendsys.utils.AESUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;

/**
 * Description: 接收并处理用户信息接口
 * author  dyenigma
 * date 2016/9/22 8:01
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    UserInfoService userInfoService;
    @Autowired
    MsgService msgService;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @GetParams
    @LogRecord(description = "存储用户信息")
    public String save(HttpServletRequest request) {

        //存储用户信息
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
        userInfoService.insert(userInfo);

        //检查消息池中有没有匹配该用户的信息
        Msg msg = msgService.getMsg(userInfo.getUserId(), userInfo.getProject());
        if (msg == null) {
            ReturnInfo returnInfo = new ReturnInfo(SysConstant.OK, null);
            if (SysConstant.NOFLAG.equals(userInfo.getKeyFlag())) {
                return JSON.toJSONString(returnInfo);
            } else {
                return encrypt(returnInfo);
            }
        } else {
            //修改信息已经发送
            msgService.modifyMsg(msg.getUserId(), msg.getProject(), msg.getRuleUuid());
            //根据不同的客户端返回不同的内容
            ReturnInfo returnInfo = new ReturnInfo(SysConstant.SUCCESS, msg);
            if (SysConstant.NOFLAG.equals(userInfo.getKeyFlag())) {
                return JSON.toJSONString(returnInfo);
            } else {
                return encrypt(returnInfo);
            }
        }
    }

    private String encrypt(ReturnInfo returnInfo) {
        String result = "";
        try {
            result = AESUtil.parseByte2HexStr(AESUtil.encrypt(JSON.toJSONString(returnInfo)));
        } catch (UnsupportedEncodingException e) {
            logger.info("字符编码转换异常：", e);
        } catch (InvalidAlgorithmParameterException e) {
            logger.info("解密异常：", e);
        }
        return result;
    }
}
