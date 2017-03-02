package net.htjs.sendsys.mongo;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Description:监控结果记录表
 * author  dyenigma
 * date 2016/10/11 11:05
 */
@Document(collection = "monitordb")
public class Monitor {
    private String className;
    private String methodName;
    private String paramLists;
    private long allTime;
    private String createTime;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getParamLists() {
        return paramLists;
    }

    public void setParamLists(String paramLists) {
        this.paramLists = paramLists;
    }

    public long getAllTime() {
        return allTime;
    }

    public void setAllTime(long allTime) {
        this.allTime = allTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
