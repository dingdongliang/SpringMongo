package net.htjs.sendsys.service.impl;

import net.htjs.sendsys.model.Project;
import net.htjs.sendsys.mongo.UserRule;
import net.htjs.sendsys.service.RuleService;
import net.htjs.sendsys.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.sort;

/**
 * Description:保存规则
 * author  dyenigma
 * date 2016/9/18 16:30
 */
@Service("ruleService")
public class RuleServiceImpl implements RuleService {

    @Autowired
    MongoTemplate mongoEventsTemplate;
    private static final int PAGE_SIZE = 10;


    @Override
    public void add(UserRule userrule) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 15; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        String UUID = sb.toString();
        userrule.setCreateTime(DateUtil.dateAndTime());
        userrule.setUuid(UUID);
        mongoEventsTemplate.insert(userrule);
    }

    @Override
    public Map getProject(int currentpage) {
        //总条数
        long total = mongoEventsTemplate.count(new Query(), UserRule.class);
        //总页数
        int totalpages = (int) Math.ceil(total / PAGE_SIZE);
        if (totalpages < 1)
            totalpages = 1;
        int skip = (currentpage - 1) * PAGE_SIZE;
        List<UserRule> rules = mongoEventsTemplate.find(new Query().skip(skip).limit(PAGE_SIZE), UserRule.class);
        Map map = new HashMap<>();
        map.put("list", rules);
        map.put("totalpages", totalpages);
        return map;
    }

    @Override
    public List<Project> getProjectList() {
        Aggregation agg = newAggregation(group("project").count().as("times"), project("times").and("project")
                .previousOperation(),
                sort(Sort.Direction.DESC, "times"));
        AggregationResults<Project> groupResults
                = mongoEventsTemplate.aggregate(agg, UserRule.class, Project.class);
        return  groupResults.getMappedResults();
    }
}
