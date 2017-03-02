package net.htjs.sendsys.service.impl;

import net.htjs.sendsys.mongo.UserInfo;
import net.htjs.sendsys.service.UserInfoService;
import net.htjs.sendsys.utils.MongoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

/**
 * Description:
 * author  dyenigma
 * date 2016/9/22 8:09
 */
@Service("userInfoService")
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    MongoTemplate mongoEventsTemplate;

    /**
     * Description: 保存用户信息
     * methodName:insert
     * Time:2016/9/23 10:12
     * param:[userInfo]
     * return:void
     */
    @Override
    public void insert(UserInfo userInfo) {

        mongoEventsTemplate.insert(userInfo, MongoUtil.INFO);
    }

    @Override
    public void update(UserInfo userInfo) {
        mongoEventsTemplate.updateFirst(new Query(Criteria.where("userId").is(userInfo.getUserId()).and("createTime").is(userInfo.getCreateTime())
        ), new Update().set("city","本地"),UserInfo.class);
    }
}
