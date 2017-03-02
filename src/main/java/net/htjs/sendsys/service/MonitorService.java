package net.htjs.sendsys.service;

import net.htjs.sendsys.mongo.Monitor;

import java.util.Map;

/**
 * Description:
 * author  dyenigma
 * date 2016/10/11 11:08
 */
public interface MonitorService {

    void saveMonitorInfo(Monitor monitor);

    /**
     * Description: 监控排序，descStr为要排序的字段名称
     * methodName:getList
     * Time:2016/10/21 11:03
     * param:[starttime, endtime, count, currentpage, descStr]
     * return:java.util.Map
     */
    Map getList(String starttime, String endtime, long count, int currentpage, String descStr);
}
