package net.htjs.sendsys.service.impl;

import net.htjs.sendsys.mongo.Monitor;
import net.htjs.sendsys.service.MonitorService;
import net.htjs.sendsys.utils.MongoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description:
 * author  dyenigma
 * date 2016/10/11 11:13
 */
@Service("monitorService")
public class MonitorServiceImpl implements MonitorService {

    @Autowired
    MongoTemplate mongoEventsTemplate;
    private static final int PAGE_SIZE = 10;

    @Override
    public void saveMonitorInfo(Monitor monitor) {
        mongoEventsTemplate.insert(monitor, MongoUtil.MONITOR);
    }

    /**
     * param starttime
     * param endtime
     * param count
     * param currentpage
     * return
     */
    @Override
    public Map getList(String starttime, String endtime, long count, int currentpage,String descStr) {
        long total = mongoEventsTemplate.count(new Query(Criteria.where("createTime").gte(starttime).lte(endtime).and
                ("allTime").gte(count)).with(new Sort(Sort.Direction.DESC, descStr)), Monitor.class);
        //总页数
        int totalpages = (int) Math.ceil(total / PAGE_SIZE);
        if (totalpages < 1)
            totalpages = 1;
        int skip = (currentpage - 1) * PAGE_SIZE;
        List<Monitor> monitorList = mongoEventsTemplate.find(new Query(Criteria.where("createTime").gte(starttime).lte
                (endtime).and("allTime").gte(count)).with(new Sort(Sort.Direction.DESC, descStr)).skip(skip).limit
                (PAGE_SIZE), Monitor.class);
        Map map = new HashMap<>();
        map.put("list", monitorList);
        map.put("totalpages", totalpages);
        return map;
    }
}
