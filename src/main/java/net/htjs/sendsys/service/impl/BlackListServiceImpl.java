package net.htjs.sendsys.service.impl;

import net.htjs.sendsys.mongo.BlackList;
import net.htjs.sendsys.service.BlackListService;
import net.htjs.sendsys.utils.MongoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description:
 * author  dyenigma
 * date 2016/9/21 8:05
 */
@Service("blackListService")
public class BlackListServiceImpl implements BlackListService {
    @Autowired
    MongoTemplate mongoEventsTemplate;
    private static final int PAGE_SIZE = 10;

    /**
     * Description: 新增黑名单
     * methodName:insert
     * Time:2016/9/23 10:11
     * param:[blackList]
     * return:void
     */
    @Override
    public void insert(BlackList blackList) {

        mongoEventsTemplate.insert(blackList, MongoUtil.BLACKLIST);
    }

    /**
     * Description: 查询黑名单是否存在当天的该IP记录，如果有查看count次数，如果超过100次返回true，说明已经被禁
     * methodName:searchByIp
     * Time:2016/9/23 10:10
     * param:[ip, day]
     * return:net.htjs.sendsys.mongo.BlackList
     */
    @Override
    public BlackList searchByIp(String ip, String day) {

        return mongoEventsTemplate.findOne(new Query(Criteria.where("ip").is(ip).and("time").is(day)), BlackList.class);
    }

    /**
     * Description: 更新黑名单中的警告次数
     * methodName:update
     * Time:2016/9/23 10:10
     * param:[blackList]
     * return:void
     */
    @Override
    public void update(BlackList blackList) {


        Update update = new Update();
        update.set("count", blackList.getCount());

        mongoEventsTemplate.updateFirst(new Query(Criteria.where("ip").is(blackList.getIp()).and("time").is(blackList
                .getTime())), update, BlackList.class);
    }

    /**
     * @param starttime
     * @param endtime
     * @param count
     * @param currentpage
     * @return
     */
    @Override
    public Map getList(String starttime, String endtime, int count, int currentpage) {
        long total = mongoEventsTemplate.count(new Query(Criteria.where("time").gte(starttime).lte(endtime).and
                ("count").gte(count)), BlackList.class);
        //总页数
        int totalpages = (int) Math.ceil(total / PAGE_SIZE);
        if (totalpages < 1)
            totalpages = 1;
        int skip = (currentpage - 1) * PAGE_SIZE;
        List<BlackList> blacklist = mongoEventsTemplate.find(new Query(Criteria.where("time").gte(starttime).lte
                (endtime).and("count").gte(count))
                .skip(skip).limit(PAGE_SIZE), BlackList.class);
        Map map = new HashMap<>();
        map.put("list", blacklist);
        map.put("totalpages", totalpages);
        return map;
    }
}
