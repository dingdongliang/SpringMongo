package net.htjs.sendsys.service.impl;

import net.htjs.sendsys.model.ProLog;
import net.htjs.sendsys.service.LogService;
import net.htjs.sendsys.utils.MongoUtil;
import org.springframework.beans.factory.annotation.Autowired;
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
 * date 2016/9/18 16:30
 */
@Service("logService")
public class LogServiceImpl implements LogService {

    @Autowired
    MongoTemplate mongoEventsTemplate;
    private static final int PAGE_SIZE = 10;

    @Override
    public void add(ProLog log) {
        mongoEventsTemplate.insert(log, MongoUtil.LOG);
    }

    @Override
    public Map getlogs(String ttime, String ip, int currentpage) {
        //判断ip的值，如果为0.0.0.0，则表示IP未传参数，默认筛选全部
        if ("0.0.0.0".equals(ip)) {
            long total = mongoEventsTemplate.count(new Query(Criteria.where("createTime").regex(ttime)), ProLog.class);
            //总页数
            int totalpages = (int) Math.ceil(total / PAGE_SIZE);
            if (totalpages < 1)
                totalpages = 1;
            int skip = (currentpage - 1) * PAGE_SIZE;
            List<ProLog> logList = mongoEventsTemplate.find(new Query(Criteria.where("createTime").regex(ttime))
                    .skip(skip).limit(PAGE_SIZE), ProLog.class);
            Map map = new HashMap<>();
            map.put("list", logList);
            map.put("totalpages", totalpages);
            return map;
        } else {
            long total = mongoEventsTemplate.count(new Query(Criteria.where("createTime").regex(ttime).and
                    ("requestIp").is(ip)), ProLog.class);
            //总页数
            int totalpages = (int) Math.ceil(total / PAGE_SIZE);
            if (totalpages < 1)
                totalpages = 1;
            int skip = (currentpage - 1) * PAGE_SIZE;
            List<ProLog> logList = mongoEventsTemplate.find(new Query(Criteria.where("createTime").regex(ttime).and
                    ("requestIp").is(ip))
                    .skip(skip).limit(PAGE_SIZE), ProLog.class);
            Map map = new HashMap<>();
            map.put("list", logList);
            map.put("totalpages", totalpages);
            return map;
        }
    }
}
