package net.htjs.sendsys.service;

import net.htjs.sendsys.mongo.IpInfo;

/**
 * Description: 检查ip地址访问频率
 * author  dyenigma
 * date 2016/9/20 17:00
 */
public interface IpCheckService {

    /**
     * Description: 新插入IP访问信息
     * methodName:insert
     * Time:2016/9/23 10:14
     * param:[ipInfo]
     * return:void
     */
    void insert(IpInfo ipInfo);

    /**
     * Description: 更新IP地址访问时间和访问次数
     * methodName:update
     * Time:2016/9/23 10:13
     * param:[ipInfo]
     * return:void
     */
    void update(IpInfo ipInfo);

    /**
     * Description: 获取IP地址最近的一次访问时间，或许会为空
     * methodName:getLastTime
     * Time:2016/9/23 10:13
     * param:[ip]
     * return:net.htjs.sendsys.mongo.IpInfo
     */
    IpInfo getLastTime(String ip);

}
