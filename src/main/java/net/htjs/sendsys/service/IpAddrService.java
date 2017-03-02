package net.htjs.sendsys.service;

import net.htjs.sendsys.mongo.IpAddr;

/**
 * Description:
 * author  dyenigma
 * date 2016/10/18 9:37
 */
public interface IpAddrService {
    IpAddr selectIpAddr(long ip);
}
