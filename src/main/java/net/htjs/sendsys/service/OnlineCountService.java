package net.htjs.sendsys.service;

import net.htjs.sendsys.mongo.OnlineCounts;

import java.util.List;

/**
 * Created by Administrator on 2016/9/27.
 */
public interface OnlineCountService {
    /**
     *  Description:根据天获取每小时的在线用户数
     * @param project
     * @param day
     * @return
     */
    List<OnlineCounts> getCountByHour(String project,String day);

    /**
     * Description:根据月获取每天的在线用户数
     * @param project
     * @param month
     * @return
     */
    List<OnlineCounts> getCountByDay(String project,String month);

    /**
     * Description:获取每天的在线用户数
     * @param project
     * @param day
     * @return
     */
    List<OnlineCounts> getCityCountByDay(String project,String day);

    /**
     *
     * @param project
     * @return
     */
    OnlineCounts getCountByMinute(String project);

    /**
     * 获取最近一天每分钟的在线用户数
     * @param project
     * @return
     */
    List<OnlineCounts> getCountByMinutes(String project);
}
