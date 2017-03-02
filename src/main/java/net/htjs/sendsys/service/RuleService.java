package net.htjs.sendsys.service;

import net.htjs.sendsys.model.Project;
import net.htjs.sendsys.mongo.UserRule;

import java.util.List;
import java.util.Map;

/**
 * Description:
 * author  dyenigma
 * date 2016/9/18 16:10
 */
public interface RuleService {
    void add(UserRule userrule);
    Map getProject(int currentpage);
    List<Project> getProjectList();
}
