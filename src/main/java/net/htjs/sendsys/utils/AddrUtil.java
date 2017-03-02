package net.htjs.sendsys.utils;

/**
 * Description:
 * author  dyenigma
 * date 2016/10/18 9:36
 */
public class AddrUtil {

    private AddrUtil(){}

    public static long ipToLong(String ip) {
        String[] scIP = ip.split("\\.");
        return Long.parseLong(scIP[0]) * 256 * 256 * 256 + Long.parseLong(scIP[1]) * 256 * 256 + Long.parseLong
                (scIP[2]) * 256 + Long.parseLong(scIP[3]);
    }
}
