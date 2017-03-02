package net.htjs.sendsys.service.impl;

import net.htjs.sendsys.mongo.OnlineCounts;
import net.htjs.sendsys.service.OnlineCountService;
import net.htjs.sendsys.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/9/27.
 */
@Service("onlineCountService")
public class OnlineCountServiceImpl implements OnlineCountService {

    @Autowired
    MongoTemplate mongoEventsTemplate;

    @Override
    public List<OnlineCounts> getCountByHour(String project, String day) {
        return mongoEventsTemplate.find(new Query(Criteria.where("project").is(project).and("countTime").regex(day)
                .and("countType").is("H")).with(new Sort(Sort.Direction.ASC, "countTime")), OnlineCounts.class);
    }

    @Override
    public List<OnlineCounts> getCountByDay(String project, String month) {
        return mongoEventsTemplate.find(new Query(Criteria.where("project").is(project).and("countTime").regex(month)
                .and("countType").is("D")).with(new Sort(Sort.Direction.ASC, "countTime")), OnlineCounts.class);
    }

    @Override
    public List<OnlineCounts> getCityCountByDay(String project, String day) {
        return mongoEventsTemplate.find(new Query(Criteria.where("project").is(project).and("countTime").is(day)
                .and("countType").is("D_CITY")), OnlineCounts.class);
    }

    @Override
    public OnlineCounts getCountByMinute(String project) {
        return mongoEventsTemplate.findOne(new Query(Criteria.where("project").is(project)
                .and("countType").is("M")).with(new Sort(Sort.Direction.DESC, "countTime")), OnlineCounts.class);
    }

    @Override
    public List<OnlineCounts> getCountByMinutes(String project) {
        Date date= new Date();
        date.setTime(date.getTime()-24*60 * 60 * 1000);
        String begin = DateUtil.date2Str("yyyy-MM-dd HH:mm",date);
        return  mongoEventsTemplate.find(new Query(Criteria.where("project").is(project).and("countTime").gte(begin)
                .and("countType").is("M")).with(new Sort(Sort.Direction.ASC, "countTime")), OnlineCounts.class);
    }
}
