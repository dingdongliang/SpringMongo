package net.htjs.sendsys.mongo;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Description: 黑名单
 * author  dyenigma
 * date 2016/9/20 17:38
 */
@Document(collection="blacklistdb")
public class BlackList {
    private String ip;
    private String time; //年月日形式
    private int count; //警告次数，每天超过设定次数，禁止再访问

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
