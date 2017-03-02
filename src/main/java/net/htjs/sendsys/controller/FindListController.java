package net.htjs.sendsys.controller;

import com.alibaba.fastjson.JSON;
import net.htjs.sendsys.annotation.GetParams;
import net.htjs.sendsys.annotation.LogRecord;
import net.htjs.sendsys.model.ProLog;
import net.htjs.sendsys.model.Project;
import net.htjs.sendsys.mongo.BlackList;
import net.htjs.sendsys.mongo.Monitor;
import net.htjs.sendsys.mongo.Msg;
import net.htjs.sendsys.mongo.UserInfo;
import net.htjs.sendsys.mongo.UserRule;
import net.htjs.sendsys.service.BlackListService;
import net.htjs.sendsys.service.LogService;
import net.htjs.sendsys.service.MonitorService;
import net.htjs.sendsys.service.RuleService;
import net.htjs.sendsys.service.UserListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Administrator on 2016/9/28.
 */
@Controller
public class FindListController {
    @Autowired
    private UserListService userlistService;
    @Autowired
    private RuleService ruleService;
    @Autowired
    private BlackListService blackListService;
    @Autowired
    private LogService logService;
    @Autowired
    private MonitorService monitorService;

    @ResponseBody
    @RequestMapping(value = "/getUserList", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @GetParams
    @LogRecord(description = "查询用户信息")
    public String getUserList(String project, String starttime, String endtime, int currentpage) {
        List<UserInfo> users = (List<UserInfo>) userlistService.getUserListByTime(project, starttime, endtime,
                currentpage).get("list");
        return JSON.toJSONString(users);
    }

    @ResponseBody
    @RequestMapping(value = "/getUserTotalPage", method = RequestMethod.POST, produces = "application/json;" +
            "charset=utf-8")
    @GetParams
    @LogRecord(description = "查询用户信息总条数")
    public String getUserTotalPage(String project, String starttime, String endtime, int currentpage) {
        int totalpages = (Integer) userlistService.getUserListByTime(project, starttime, endtime, currentpage).get
                ("totalpages");
        return JSON.toJSONString(totalpages);
    }

    @ResponseBody
    @RequestMapping(value = "/getMsgList", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @GetParams
    @LogRecord(description = "查询已发送信息")
    public String getMsgList(String project, String starttime, String endtime, int currentpage) {
        List<Msg> msgs = (List<Msg>) userlistService.getUserListByMsg(project, starttime, endtime, currentpage).get
                ("list");
        return JSON.toJSONString(msgs);
    }

    @ResponseBody
    @RequestMapping(value = "/getMsgTotalPage", method = RequestMethod.POST, produces = "application/json;" +
            "charset=utf-8")
    @GetParams
    @LogRecord(description = "查询已发送信息总条数")
    public String getMsgTotalPage(String project, String starttime, String endtime, int currentpage) {
        int totalpages = (Integer) userlistService.getUserListByMsg(project, starttime, endtime, currentpage).get
                ("totalpages");
        return String.valueOf(totalpages);
    }

    @ResponseBody
    @RequestMapping(value = "/getRuleList", produces = "application/json;charset=utf-8")
    @GetParams
    @LogRecord(description = "查询已制定的规则")
    public String getRuleList(int currentpage) {
        List<UserRule> rules = (List<UserRule>) ruleService.getProject(currentpage).get("list");
        return JSON.toJSONString(rules);
    }

    @ResponseBody
    @RequestMapping(value = "/getRuleTotalPage", produces = "application/json;charset=utf-8")
    @GetParams
    @LogRecord(description = "查询规则总页数")
    public String getRuleTotalPage(int currentpage) {
        int totalpages = (Integer) ruleService.getProject(currentpage).get("totalpages");
        return String.valueOf(totalpages);
    }

    @ResponseBody
    @RequestMapping(value = "/getProjectList", produces = "application/json;charset=utf-8")
    @GetParams
    @LogRecord(description = "查询项目列表")
    public String getProjectList() {
        List<Project> rules = ruleService.getProjectList();
        return JSON.toJSONString(rules);
    }

    @ResponseBody
    @RequestMapping(value = "/getBlackList", produces = "application/json;charset=utf-8")
    @GetParams
    @LogRecord(description = "查询黑名单列表")
    public String getBlackList(String starttime, String endtime, int count, int currentpage) {
        List<BlackList> blackList = (List<BlackList>) blackListService.getList(starttime, endtime, count,
                currentpage).get("list");
        return JSON.toJSONString(blackList);
    }

    @ResponseBody
    @RequestMapping(value = "/getBlackTotalPage", produces = "application/json;charset=utf-8")
    @GetParams
    @LogRecord(description = "查询黑名单总页数")
    public String getBlackTotalPage(String starttime, String endtime, int count, int currentpage) {
        int totalpages = (Integer) blackListService.getList(starttime, endtime, count, currentpage).get("totalpages");
        return String.valueOf(totalpages);
    }

    @ResponseBody
    @RequestMapping(value = "/getMonitorList", produces = "application/json;charset=utf-8")
    @GetParams
    @LogRecord(description = "查询性能监控列表")
    public String getMonitorList(String starttime, String endtime, int count, int currentpage, String descStr) {
        List<Monitor> monitorList = (List<Monitor>) monitorService.getList(starttime, endtime, count,
                currentpage, descStr).get("list");
        return JSON.toJSONString(monitorList);
    }

    @ResponseBody
    @RequestMapping(value = "/getMonitorTotalPage", produces = "application/json;charset=utf-8")
    @GetParams
    @LogRecord(description = "查询性能监控总页数")
    public String getMonitorTotalPage(String starttime, String endtime, int count, int currentpage, String descStr) {
        int totalpages = (Integer) monitorService.getList(starttime, endtime, count, currentpage, descStr).get
                ("totalpages");
        return String.valueOf(totalpages);
    }

    @ResponseBody
    @RequestMapping(value = "/getLogList", produces = "application/json;charset=utf-8")
    @GetParams
    @LogRecord(description = "查询操作日志列表")
    public String getLogList(String ttime, String ip, int currentpage) {
        List<ProLog> logList = (List<ProLog>) logService.getlogs(ttime, ip, currentpage).get("list");
        return JSON.toJSONString(logList);
    }

    @ResponseBody
    @RequestMapping(value = "/getLogTotalPage", produces = "application/json;charset=utf-8")
    @GetParams
    @LogRecord(description = "查询操作日志总页数")
    public String getLogTotalPage(String ttime, String ip, int currentpage) {
        int totalpages = (Integer) logService.getlogs(ttime, ip, currentpage).get("totalpages");
        return String.valueOf(totalpages);
    }


}
