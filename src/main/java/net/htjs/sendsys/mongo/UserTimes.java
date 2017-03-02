package net.htjs.sendsys.mongo;

/**
 * 一段时间内采集到的用户次数
 * Created by Administrator on 2016/9/23.
 */
public class UserTimes {
    private String project;
    private String userId;
    private Integer times;
    private String requestAddr;

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

    public Integer getTimes() {
        return times;
    }

    public void setTimes(Integer times) {
        this.times = times;
    }

    public String getRequestAddr() {
        return requestAddr;
    }

    public void setRequestAddr(String requestAddr) {
        this.requestAddr = requestAddr;
    }
}
