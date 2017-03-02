package net.htjs.sendsys.model;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Description: 日志类
 * author  dyenigma
 * date 2016/9/18 16:12
 */
@Document(collection="logdb")
public class ProLog {

    private String description; //日志描述，可以直接获取AOP中的description

    //方法名，AOP中的拼接方式如下：
    //类名：joinPoint.getTarget().getClass().getName()
    //方法名：joinPoint.getSignature().getName()
    private String method;

    private String requestIp; //请求IP
    private String errCode;   //错误代码
    private String errDetail; //错误细节

    private String create; //当前用户名或者操作用户名
    private String createTime; //日志时间，一般是DateUtil.dateAndTime()

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }


    public String getRequestIp() {
        return requestIp;
    }

    public void setRequestIp(String requestIp) {
        this.requestIp = requestIp;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrDetail() {
        return errDetail;
    }

    public void setErrDetail(String errDetail) {
        this.errDetail = errDetail;
    }


    public String getCreate() {
        return create;
    }

    public void setCreate(String create) {
        this.create = create;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
