package net.htjs.sendsys.task;

import net.htjs.sendsys.annotation.GetParams;
import net.htjs.sendsys.annotation.LogRecord;
import net.htjs.sendsys.model.SysConstant;
import net.htjs.sendsys.mongo.Msg;
import net.htjs.sendsys.mongo.UserInfo;
import net.htjs.sendsys.mongo.UserRule;
import net.htjs.sendsys.service.MsgService;
import net.htjs.sendsys.utils.DateUtil;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Description:定时更新消息池消息
 * author  dyenigma
 * date 2016/9/12 16:42
 */
public class CreateMsgQtz extends QuartzJobBean {

    @Autowired
    MongoTemplate mongoEventsTemplate;
    @Autowired
    MsgService msgService;

    @Override
    @GetParams
    @LogRecord(description = "定时器更新消息池消息")
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        //查找所有的筛选规则，暂定每个项目只有一条规则，规则中flag=1，规则有效日期在当前日期范围内
        List<UserRule> userRules = mongoEventsTemplate.find(new Query(Criteria.where("flag").is("1")
                .and("beginTime").lt(DateUtil.dateAndTime())
                .and("endTime").gt(DateUtil.dateAndTime())), UserRule.class);
        //循环所有的规则
        for (UserRule userRule : userRules) {
//            String rule = userRule.getRule1();
//            if (!"".equals(rule) && rule != null) {
//                String abc = rule.substring(0, userRule.getRule1().length() - 1);
//                String[] arr = abc.split(",");
            String rule2 = userRule.getRule2();
            String[] nums;
            if (!"".equals(rule2) && rule2 != null) {
                nums = rule2.split(",");
                for (String str : nums) {
                    Msg msg = new Msg();
                    msg.setRuleUuid(userRule.getUuid());
                    msg.setUserId(str);
                    msg.setProject(userRule.getProject());
                    msg.setMsgContent(userRule.getMsg());
                    msg.setFlag(SysConstant.NOFLAG);//默认没有发送,"0"
                    msg.setMsgKeepTime(1000);
                    msg.setCreate_Timestamp(DateUtil.dateAndTime());
                    msg.setTime(userRule.getTime());
                    msg.setUpdate(userRule.isUpdate());
                    msg.setMsgTitle(userRule.getMsgTitle());
                    msg.setMsgType(userRule.getMsgType());
                    if (userRule.isUpdate()) {
                        msg.setUpdateUrl(userRule.getUpdateUrl());
                    }
                    msg.setClose(userRule.getClose());
                    //当日零点
                    String zero = new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + " 00:00:01";
                    //判断同一条规则当天未发送的消息条数
                    Long count = mongoEventsTemplate.count(
                            new Query(Criteria.where("userId").is(str).and("project").is(userRule.getProject())
                                    .and("create_Timestamp").gt(zero).and("ruleUuid").is(userRule.getUuid())), Msg
                                    .class);
                    //如果当天同意规则有消息，则不添加
                    if (count < new Long(1)) {
                        mongoEventsTemplate.insert(msg);
                    }
                }

            } else {


                //根据用户筛选规则筛选出用户，筛选最近一小时采集到的用户，如果筛选范围扩大，调整-1参数就行
                List<UserInfo> infos = mongoEventsTemplate.find(new Query(Criteria.where("project").is(userRule
                                .getProject()).and("createTime").gt(DateUtil.getSpecifiedDay(-1))),
                        UserInfo.class);
                //循环将满足条件的用户插入到消息池
                for (UserInfo info : infos) {
//                    if (Arrays.asList(arr).contains(info.getRequestAddr())) {
                    Msg msg = new Msg();
                    msg.setRuleUuid(userRule.getUuid());
                    msg.setUserId(info.getUserId());
                    msg.setProject(info.getProject());
                    msg.setMsgContent(userRule.getMsg());
                    msg.setFlag(SysConstant.NOFLAG);//默认没有发送,"0"
                    msg.setMsgKeepTime(1000);
                    msg.setCreate_Timestamp(DateUtil.dateAndTime());
                    msg.setTime(userRule.getTime());
                    msg.setUpdate(userRule.isUpdate());
                    msg.setMsgTitle(userRule.getMsgTitle());
                    msg.setMsgType(userRule.getMsgType());
                    if (userRule.isUpdate()) {
                        msg.setUpdateUrl(userRule.getUpdateUrl());
                    }
                    msg.setClose(userRule.getClose());
                    //当日零点
                    String zero = new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + " 00:00:01";
                    //判断同一条规则当天未发送的消息条数
                    Long count = mongoEventsTemplate.count(
                            new Query(Criteria.where("userId").is(info.getUserId()).and("project").is(info
                                    .getProject())

                                    .and("create_Timestamp").gt(zero).and("ruleUuid").is(userRule.getUuid())), Msg
                                    .class);
                    //如果当天同意规则有消息，则不添加
                    if (count < new Long(1)) {
                        mongoEventsTemplate.insert(msg);
                    }
                }
            }

//            }
        }
    }
}
