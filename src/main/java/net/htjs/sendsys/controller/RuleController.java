package net.htjs.sendsys.controller;

import net.htjs.sendsys.annotation.GetParams;
import net.htjs.sendsys.annotation.LogRecord;
import net.htjs.sendsys.mongo.UserRule;
import net.htjs.sendsys.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Description: 根据设定的用户筛选规则，生成消息池
 * Created by Administrator on 2016/9/18.
 */
@Controller
public class RuleController {
    @Autowired
    private RuleService ruleService;

    @RequestMapping(value = "/saveRule",method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @GetParams
    @LogRecord(description = "保存规则")
    @ResponseBody
    public void saveRule(@ModelAttribute("userRule") UserRule userRule) {
        ruleService.add(userRule);
    }


}
