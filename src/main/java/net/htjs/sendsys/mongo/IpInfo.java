package net.htjs.sendsys.mongo;

import net.htjs.sendsys.utils.DateUtil;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Description: 访问的Ip地址信息
 * author  dyenigma
 * date 2016/9/20 17:11
 */
@Document(collection = "ipdb")
public class IpInfo {
    private String ip; //ip地址
    private String requestTime; //最近一次访问时间
    private int requestCount; //访问次数

    public IpInfo() {
        this.requestTime = DateUtil.dateAndTime();
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(String requestTime) {
        this.requestTime = requestTime;
    }

    public int getRequestCount() {
        return requestCount;
    }

    public void setRequestCount(int requestCount) {
        this.requestCount = requestCount;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
