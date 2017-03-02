package net.htjs.sendsys.service.impl;

import net.htjs.sendsys.mongo.Msg;
import net.htjs.sendsys.service.MsgService;
import net.htjs.sendsys.utils.DateUtil;
import net.htjs.sendsys.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Description:获取消息池消息，修改消息池消息状态
 * date 2016/9/12 17:29
 */
@Service("msgService")
public class MsgServiceImpl implements MsgService {

    @Autowired
    MongoTemplate mongoEventsTemplate;

    /**
     * Description:根据用户ID，项目名称从消息池获取消息
     *
     * @param project
     * @param userId
     * @return Msg
     */
    @Override
    public Msg getMsg(String userId, String project) {
        String zero = new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + " 00:00:01";
        return mongoEventsTemplate.findOne(new Query(Criteria.where("userId").is(userId).and("project").is(project)
                .and("flag").is("0").and("create_Timestamp").gte(zero)).with(new Sort(Sort.Direction.ASC,
                "create_Timestamp")), Msg.class);
    }

    /**
     * Description:根据用户ID，项目名称,消息发送成功后修改flag值为1
     *
     * @param project
     * @param userId
     * @return Msg
     */
    @Override
    public void modifyMsg(String userId, String project, String uuid) {
        //当日零点
        String zero = new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + " 00:00:01";
        mongoEventsTemplate.findAndModify(new Query(Criteria.where("userId").is(userId).and("project").is(project)
                        .and("create_Timestamp").gte(zero).and("ruleUuid").is(uuid)),
                new Update().set("flag", "1").set("send_Timestamp", DateUtil.dateAndTime()), Msg.class);

    }

    @Override
    public void modifyMsgOut(String userId, String project, String uuid) {
        //当日零点
        String zero = new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + " 00:00:01";
        mongoEventsTemplate.findAndModify(new Query(Criteria.where("userId").is(userId).and("project").is(project)
                        .and("create_Timestamp").gte(zero).and("ruleUuid").is(uuid)),
                new Update().set("flag", "2"), Msg.class);
    }

    @Override
    public void saveMsg(Msg msg) {
        msg.setRuleUuid(UUIDUtil.getUUID());
        msg.setFlag("0");
        msg.setMsgKeepTime(1000);
        msg.setCreate_Timestamp(DateUtil.dateAndTime());
        mongoEventsTemplate.insert(msg);
    }
}
