package net.htjs.sendsys.mongo;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Description:IP地址匹配，计算方法为IP转化为256进制数
 * author  dyenigma
 * date 2016/10/18 9:28
 */
@Document(collection = "ipAddrdb")
public class IpAddr {

    private String ipStart; //IP号段起始
    private String ipEnd; //IP号段结束
    private Long ipLt; //根据结束号段计算出来的IP值
    private Long ipGt; //根据起始号段计算出来的IP值
    private String addr;  //IP号段所在地区
    private String company; //IP号段所属运营商名称

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getIpStart() {
        return ipStart;
    }

    public void setIpStart(String ipStart) {
        this.ipStart = ipStart;
    }

    public String getIpEnd() {
        return ipEnd;
    }

    public void setIpEnd(String ipEnd) {
        this.ipEnd = ipEnd;
    }

    public Long getIpLt() {
        return ipLt;
    }

    public void setIpLt(Long ipLt) {
        this.ipLt = ipLt;
    }

    public Long getIpGt() {
        return ipGt;
    }

    public void setIpGt(Long ipGt) {
        this.ipGt = ipGt;
    }
}
