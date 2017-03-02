package net.htjs.sendsys.mongo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Description:消息池消息
 * Created by Administrator on 2016/9/14.
 */
@Document(collection = "msgdb")
public class Msg {

    private String userId;//用户ID
    private String msgContent;//消息内容
    private String msgTitle;//消息标题
    private String msgType;//消息类型
    private String project;//项目名称
    private Integer msgKeepTime;//消息保持时间
    private String flag;//是否已发送
    private String create_Timestamp;//消息创建时间
    private String send_Timestamp;//消息发送时间

    private String time; //频率指示,只能是up或down
    private boolean update; //升级通知,默认为false
    private String updateUrl; //升级的链接
    private String close; //关闭形式，all一起关闭|own关闭自己
    private String ruleUuid;//消息对应的规则UUID

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isUpdate() {
        return update;
    }

    public void setUpdate(boolean update) {
        this.update = update;
    }

    public String getUpdateUrl() {
        return updateUrl;
    }

    public void setUpdateUrl(String updateUrl) {
        this.updateUrl = updateUrl;
    }

    public String getClose() {
        return close;
    }

    public void setClose(String close) {
        this.close = close;
    }

    public String getCreate_Timestamp() {
        return create_Timestamp;
    }

    public void setCreate_Timestamp(String create_Timestamp) {
        this.create_Timestamp = create_Timestamp;
    }

    public String getSend_Timestamp() {
        return send_Timestamp;
    }

    public void setSend_Timestamp(String send_Timestamp) {
        this.send_Timestamp = send_Timestamp;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public Integer getMsgKeepTime() {
        return msgKeepTime;
    }

    public void setMsgKeepTime(Integer msgKeepTime) {
        this.msgKeepTime = msgKeepTime;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }


    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public String getRuleUuid() {
        return ruleUuid;
    }

    public void setRuleUuid(String ruleUuid) {
        this.ruleUuid = ruleUuid;
    }

    public String getMsgTitle() {
        return msgTitle;
    }

    public void setMsgTitle(String msgTitle) {
        this.msgTitle = msgTitle;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }
}
