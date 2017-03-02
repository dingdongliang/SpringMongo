package net.htjs.sendsys.task;

import net.htjs.sendsys.mongo.OnlineCounts;
import net.htjs.sendsys.mongo.UserInfo;
import net.htjs.sendsys.mongo.UserRule;
import net.htjs.sendsys.mongo.UserTimes;
import net.htjs.sendsys.utils.DateUtil;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.sort;

/**
 * Description:定时统计每天在线用户数
 * author  dyenigma
 * date 2016/9/12 16:42
 */
public class CountOnlineByDayQtz extends QuartzJobBean {

    @Autowired
    MongoTemplate mongoEventsTemplate;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        String begin = DateUtil.getSpecifiedDay(-24).substring(0, 11) + "00:00:00";
        String end = DateUtil.getSpecifiedDay(-24).substring(0, 11) + "23:59:59";

        //查出满足条件的现有规则
        List<UserRule> userRules = mongoEventsTemplate.find(new Query(Criteria.where("flag").is("1")
                .and("beginTime").lt(DateUtil.dateAndTime())
                .and("endTime").gt(DateUtil.dateAndTime())), UserRule.class);
        //循环符合条件的规则中的项目名称，统计前一天在线的用户数
        for (UserRule u : userRules) {
            Aggregation agg = newAggregation(match(Criteria.where("project").is(u.getProject()).and("createTime").gte
                            (begin).lt(end)), group("userId", "project").count().as("times"),
                    project("times").and("userId").previousOperation(), sort(Sort.Direction.DESC, "times"));
            AggregationResults<UserTimes> groupResults
                    = mongoEventsTemplate.aggregate(agg, UserInfo.class, UserTimes.class);
            List<UserTimes> result = groupResults.getMappedResults();
            //在线用户数为result的size
            OnlineCounts counts = new OnlineCounts();
            counts.setCountTime(DateUtil.getSpecifiedDay(-24).substring(0, 10));
            counts.setOnlineCounts(result.size());
            counts.setProject(u.getProject());
            counts.setCountType("D");
            //将统计结果保存到数据库
            mongoEventsTemplate.save(counts);
        }
    }
}
