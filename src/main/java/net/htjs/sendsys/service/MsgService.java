package net.htjs.sendsys.service;


import net.htjs.sendsys.mongo.Msg;

/**
 * Description: 测试接口
 * author  dyenigma
 * date 2016/9/12 17:26
 */
public interface MsgService {

    Msg getMsg(String userId, String project);

    void modifyMsg(String userId, String project,String uuid);

    void modifyMsgOut(String userId, String project,String uuid);

    void saveMsg(Msg msg);

}
