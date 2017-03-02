package net.htjs.sendsys.controller;

import net.htjs.sendsys.annotation.LogRecord;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Description:后台页面跳转控制器
 * 配置自动查询路径为WEB-INF,所以跳转到哪个页面，要写全路径，跳转页面的扩展名为jsp
 * 比如：跳转到WEB-INF/show/demo.jsp页面，就要return "show/demo"
 * author  dyenigma
 * date 2016/9/23 8:12
 */
@Controller
@RequestMapping(value = "/manage")
public class ManageController {

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    @LogRecord(description = "访问后台主页")
    public String main() {
        return "manage/main";
    }

    @RequestMapping(value = "/log", method = RequestMethod.GET)
    @LogRecord(description = "访问操作日志")
    public String log() {
        return "manage/log";
    }

    @RequestMapping(value = "/msgcenter", method = RequestMethod.GET)
    @LogRecord(description = "访问信息中心")
    public String msgcenter() {
        return "manage/msgcenter";
    }

    @RequestMapping(value = "/project", method = RequestMethod.GET)
    @LogRecord(description = "访问项目详情页")
    public String project() {
        return "manage/project";
    }

    @RequestMapping(value = "/show", method = RequestMethod.GET)
    @LogRecord(description = "演示页面")
    public String show() {
        return "manage/show";
    }

    @RequestMapping(value = "/monitor", method = RequestMethod.GET)
    @LogRecord(description = "性能监控")
    public String monitor() {
        return "manage/monitor";
    }

    @RequestMapping(value = "/analyses", method = RequestMethod.GET)
    @LogRecord(description = "数据分析")
    public String analyses() {
        return "manage/analyses";
    }
}
