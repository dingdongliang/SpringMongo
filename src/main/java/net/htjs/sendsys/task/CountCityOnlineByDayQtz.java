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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.sort;

/**
 * Description:定时统计每天在线用户数(按照IP归属地统计)
 * author  dyenigma
 * date 2016/9/12 16:42
 */
public class CountCityOnlineByDayQtz extends QuartzJobBean {

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
                            (begin).lte(end)), group("userId", "requestAddr").count().as("times"),
                    sort(Sort.Direction.DESC, "times"));
            AggregationResults<UserTimes> groupResults
                    = mongoEventsTemplate.aggregate(agg, UserInfo.class, UserTimes.class);
            List<UserTimes> result = groupResults.getMappedResults();
            List<String> list = new ArrayList<String>();
            for (UserTimes ut : result) {
                list.add(ut.getRequestAddr().toString().substring(3));
            }
            //计算每个地市出现的次数
            Map map = new HashMap();
            for (String temp : list) {
                Integer count = (Integer) map.get(temp);
                map.put(temp, (count == null) ? 1 : count + 1);
            }
            Iterator it = map.keySet().iterator();
            while (it.hasNext()) {
                String key = it.next().toString();
                //在线用户数为result的size
                OnlineCounts counts = new OnlineCounts();
                counts.setCountTime(DateUtil.getSpecifiedDay(-24).substring(0, 10));
                counts.setProject(u.getProject());
                counts.setCountType("D_CITY");
                //保存市
                counts.setCity(key);
                //获取每个市的用户量，赋值
                counts.setOnlineCounts(Integer.parseInt(map.get(key).toString()));
                //将统计结果保存到数据库
                mongoEventsTemplate.save(counts);
            }

        }
    }
}
