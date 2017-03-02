package net.htjs.sendsys.controller;

import net.htjs.sendsys.annotation.GetParams;
import net.htjs.sendsys.annotation.LogRecord;
import net.htjs.sendsys.mongo.Msg;
import net.htjs.sendsys.service.MsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Description: 根据设定的用户筛选规则，生成消息池
 * Created by Administrator on 2016/9/18.
 */
@Controller
public class MsgController {
    @Autowired
    private MsgService msgService;

    @ResponseBody
    @RequestMapping(value = "/getMsg")
    @GetParams
    @LogRecord(description = "获取消息池消息")
    public void getMsg(String userid, String project) {
        msgService.getMsg(userid, project);
    }

    @ResponseBody
    @RequestMapping(value = "/modifyMsg")
    @GetParams
    @LogRecord(description = "更新消息池消息")
    public void modifyMsg(String userid, String project, String ruleUuid) {
        msgService.modifyMsg(userid, project, ruleUuid);
    }

    @ResponseBody
    @RequestMapping(value = "/saveMsg", produces = "application/json;charset=utf-8")
    @GetParams
    @LogRecord(description = "根据用户单独写入消息池消息")
    public String saveMsg(Msg msg) {
        //存储用户信息
        msgService.saveMsg(msg);
        return "success";
    }
}
