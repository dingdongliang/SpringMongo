package net.htjs.sendsys.service.impl;

import net.htjs.sendsys.mongo.IpAddr;
import net.htjs.sendsys.service.IpAddrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

/**
 * Description:
 * author  dyenigma
 * date 2016/10/18 9:38
 */
@Service("ipAddrService")
public class IpAddrServiceImpl implements IpAddrService {

    @Autowired
    MongoTemplate mongoEventsTemplate;

    @Override
    public IpAddr selectIpAddr(long ip) {
        return mongoEventsTemplate.findOne(new Query(Criteria.where("ipLt").gte(ip)
                .and("ipGt").lte(ip)), IpAddr.class);
    }
}
