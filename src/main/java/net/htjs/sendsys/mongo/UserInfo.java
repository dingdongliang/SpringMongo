package net.htjs.sendsys.mongo;

import org.springframework.data.mongodb.core.mapping.Document;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Description: 传递过来的各种参数,用户信息
 * author  dyenigma
 * date 2016/9/18 16:52
 */
@Document(collection = "infodb")
public class UserInfo {

    private String clientType; //客户端类型：android、IOS、IE、Chrome等
    private String clientVersion; //客户端版本号
    private String clientId; //客户端生成的32位唯一标识，可以为空
    private String project; //项目名称
    private String userId; //用户ID
    private String keyFlag; //是否加密：0未加密、1加密
    private String otherData; //其他信息，一般为JSON字符串，可以为空
    private String requestIp; //访问IP地址
    private String requestAddr; //访问所在地
    private String netCompany; //所在的运营商
    private String createTime; //访问时间 yyyy-MM-dd HH:mm:ss
    private String city;//归属地
    private String clientUrl;//客户访问url

    public String getClientUrl() {
        return clientUrl;
    }

    public void setClientUrl(String clientUrl) {
        this.clientUrl = clientUrl;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getRequestIp() {
        return requestIp;
    }

    public void setRequestIp(String requestIp) {
        this.requestIp = requestIp;
    }

    public String getClientType() {
        return clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }

    public String getClientVersion() {
        return clientVersion;
    }

    public void setClientVersion(String clientVersion) {
        this.clientVersion = clientVersion;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getKeyFlag() {
        return keyFlag;
    }

    public void setKeyFlag(String keyFlag) {
        this.keyFlag = keyFlag;
    }

    public String getOtherData() {
        return otherData;
    }

    public void setOtherData(String otherData) {
        this.otherData = otherData;
    }

    public String getRequestAddr() {
        return requestAddr;
    }

    public void setRequestAddr(String requestAddr) {
        this.requestAddr = requestAddr;
    }

    public String getNetCompany() {
        return netCompany;
    }

    public void setNetCompany(String netCompany) {
        this.netCompany = netCompany;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
