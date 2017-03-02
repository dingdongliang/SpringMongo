package net.htjs.sendsys.service;

import net.htjs.sendsys.model.ProLog;

import java.util.Map;

/**
 * Description:切面日志写入接口
 * author  dyenigma
 * date 2016/9/18 16:10
 */
public interface LogService {
    void add(ProLog log);

    Map getlogs(String ttime, String ip, int currentpage);
}
