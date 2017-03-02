package net.htjs.sendsys.service;

import net.htjs.sendsys.mongo.UserInfo;

/**
 * Description: 保存客户端提交过来的用户信息
 * author  dyenigma
 * date 2016/9/22 7:59
 */
public interface UserInfoService {

    /**
     * 保存用户信息
     */
    void insert(UserInfo userInfo);

    /**
     * 更新用户IP归属地
     * @param userInfo
     */
    void update(UserInfo userInfo);
}
