package net.htjs.sendsys.test.mongodb;

import net.htjs.sendsys.mongo.UserInfo;
import net.htjs.sendsys.utils.DateUtil;
import net.htjs.sendsys.utils.MongoUtil;
import net.htjs.sendsys.utils.UUIDUtil;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Description:
 * author  dyenigma
 * date 2016/9/23 11:52
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring.xml", "classpath:spring-mvc.xml", "classpath:spring-mongodb.xml"})
public class UserTest {
    @Autowired
    MongoTemplate mongoEventsTemplate;

    @BeforeClass
    public static void init() {

    }


    @Test
    public void insert() {
        UserInfo userInfo = new UserInfo();
        userInfo.setClientId(UUIDUtil.getUUID());
        userInfo.setClientType("Chrome");
        userInfo.setClientVersion("46.0.9");
        userInfo.setCreateTime(DateUtil.dateAndTime());
        userInfo.setKeyFlag("1");
        userInfo.setRequestIp("10.3.105.16");
        userInfo.setProject("myProject");
        userInfo.setUserId("1400091");

        mongoEventsTemplate.insert(userInfo, MongoUtil.INFO);
    }

    @AfterClass
    public static void destory() {

    }
}
