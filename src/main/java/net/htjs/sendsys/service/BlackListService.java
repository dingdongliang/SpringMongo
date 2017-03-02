package net.htjs.sendsys.service;

import net.htjs.sendsys.mongo.BlackList;

import java.util.List;
import java.util.Map;

/**
 * Description: 黑名单管理
 * author  dyenigma
 * date 2016/9/20 17:37
 */
public interface BlackListService {
    /**
     * Description: 新增黑名单
     * methodName:insert
     * Time:2016/9/23 10:12
     * param:[blackList]
     * return:void
     */
    void insert(BlackList blackList);

    /**
     * Description: 查询黑名单是否存在当天的该IP记录，如果有查看count次数，如果超过100次返回true，说明已经被禁
     * methodName:searchByIp
     * Time:2016/9/23 10:13
     * param:[ip, day]
     * return:net.htjs.sendsys.mongo.BlackList
     */
    BlackList searchByIp(String ip, String day);

    /**
     * Description: 更新黑名单中的警告次数
     * methodName:update
     * Time:2016/9/23 10:13
     * param:[blackList]
     * return:void
     */
    void update(BlackList blackList);

    /**
     *
     * @param starttime
     * @param endtime
     * @param count
     * @param currentpage
     * @return
     */
    Map getList(String starttime, String endtime, int count, int currentpage);
}
