package net.htjs.sendsys.utils;

import net.htjs.sendsys.model.SysConstant;

import javax.servlet.http.HttpServletRequest;

/**
 * Description: 从代理中获取请求的真实IP地址
 * author  dyenigma
 * date 2016/9/14 10:16
 */
public class IpUtil {
    private IpUtil() {
    }

    public static String getIpAddr(HttpServletRequest request) {
        if (request == null) {
            return SysConstant.UNKNOWN;
        }

        // 取X-Forwarded-For中第一个非unknown的有效IP字符串。
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || SysConstant.UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }

        if (ip == null || ip.length() == 0 || SysConstant.UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }

        if (ip == null || ip.length() == 0 || SysConstant.UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
