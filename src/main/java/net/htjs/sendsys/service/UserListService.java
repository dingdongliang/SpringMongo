package net.htjs.sendsys.service;

import java.util.Map;

/**
 * Description:查询用户实现类
 * author
 * date 2016/9/18 16:10
 */
public interface UserListService {
    Map getUserListByTime(String project, String starttime, String endtime, int currentpage);

    Map getUserListByMsg(String project, String starttime, String endtime, int currentpage);
}
