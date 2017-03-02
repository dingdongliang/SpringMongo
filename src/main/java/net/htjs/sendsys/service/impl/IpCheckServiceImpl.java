package net.htjs.sendsys.service.impl;

import net.htjs.sendsys.mongo.IpInfo;
import net.htjs.sendsys.service.IpCheckService;
import net.htjs.sendsys.utils.MongoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

/**
 * Description:IP访问频率检查
 * author  dyenigma
 * date 2016/9/20 17:26
 */
@Service("ipCheckService")
public class IpCheckServiceImpl implements IpCheckService {

    @Autowired
    MongoTemplate mongoEventsTemplate;

    /**
     * Description: 新插入IP访问信息
     * methodName:insert
     * Time:2016/9/23 10:12
     * param:[ipInfo]
     * return:void
     */
    @Override
    public void insert(IpInfo ipInfo) {

        mongoEventsTemplate.insert(ipInfo, MongoUtil.IPVISIT);
    }

    /**
     * Description: 更新IP地址访问时间和访问次数
     * methodName:update
     * Time:2016/9/23 10:11
     * param:[ipInfo]
     * return:void
     */
    @Override
    public void update(IpInfo ipInfo) {

        Update update = new Update();
        update.set("requestTime", ipInfo.getRequestTime());
        update.set("requestCount", ipInfo.getRequestCount());

        mongoEventsTemplate.updateFirst(new Query(Criteria.where("ip").is(ipInfo.getIp())), update, IpInfo.class);
    }

    /**
     * Description:  获取IP地址最近的一次访问时间，或许会为空
     * methodName:getLastTime
     * Time:2016/9/23 10:11
     * param:[ip]
     * return:net.htjs.sendsys.mongo.IpInfo
     */
    @Override
    public IpInfo getLastTime(String ip) {

        return mongoEventsTemplate.findOne(new Query(Criteria.where("ip").is(ip)), IpInfo.class);
    }

}
