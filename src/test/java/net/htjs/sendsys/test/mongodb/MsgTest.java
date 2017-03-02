package net.htjs.sendsys.test.mongodb;

import net.htjs.sendsys.mongo.Msg;
import net.htjs.sendsys.utils.DateUtil;
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
 * Description:
 * author  dyenigma
 * date 2016/9/22 10:13
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring.xml", "classpath:spring-mvc.xml", "classpath:spring-mongodb.xml"})
public class MsgTest {

    @Autowired
    MongoTemplate mongoEventsTemplate;

    @BeforeClass
    public static void init() {

    }

    @Test
    public void find() {
        Msg msg = mongoEventsTemplate.findOne(new Query(Criteria.where("userId").is("user3")), Msg.class);
        if (msg != null)
            System.out.println(msg.toString());
    }

    @Test
    public void insert() {
        Msg msg = new Msg();
        msg.setUserId("user3");
        msg.setMsgContent("这是消息文本");
        msg.setProject("project4");
        msg.setMsgKeepTime(1000);
        msg.setFlag("0");
        msg.setCreate_Timestamp(DateUtil.dateAndTime());
        msg.setUpdate(false);
        msg.setClose("own");

        mongoEventsTemplate.insert(msg, MongoUtil.MSG);
    }

    @AfterClass
    public static void destory() {

    }
}
