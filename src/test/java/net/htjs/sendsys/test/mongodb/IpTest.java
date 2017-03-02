package net.htjs.sendsys.test.mongodb;

import net.htjs.sendsys.mongo.IpInfo;
import net.htjs.sendsys.utils.MongoUtil;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Description: Ip地址操作测试类
 * author  dyenigma
 * date 2016/9/21 17:00
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring.xml", "classpath:spring-mvc.xml", "classpath:spring-mongodb.xml"})
public class IpTest {

    @Autowired
    MongoTemplate mongoEventsTemplate;

    @BeforeClass
    public static void init() {

    }

    @Test
    public void insert() {
        IpInfo ipInfo = new IpInfo();
        ipInfo.setIp("10.3.105.16");
        ipInfo.setRequestTime("2016-09-21 17:13:12");
        ipInfo.setRequestCount(1);

        mongoEventsTemplate.insert(ipInfo, MongoUtil.IPVISIT);
    }

    @Test
    public void find() {
        IpInfo ipInfo = mongoEventsTemplate.findOne(new Query(Criteria.where("ip").is("127.0.0.1")), IpInfo.class);
        if (ipInfo != null)
            System.out.println(ipInfo.toString());
    }

    @AfterClass
    public static void destory() {

    }
}
