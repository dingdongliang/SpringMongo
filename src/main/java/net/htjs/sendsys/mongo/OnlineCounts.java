package net.htjs.sendsys.mongo;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Description:每小时在线用户数
 * Created by Administrator on 2016/9/23.
 */
@Document(collection="onlincountsdb")
public class OnlineCounts {
    private String project;//项目名称
    private String countTime;//统计时间
    private Integer onlineCounts;//在线用户数
    private String countType;//统计类型：D按天，H按小时
    private String city;//归属地

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getCountTime() {
        return countTime;
    }

    public void setCountTime(String countTime) {
        this.countTime = countTime;
    }

    public Integer getOnlineCounts() {
        return onlineCounts;
    }

    public void setOnlineCounts(Integer onlineCounts) {
        this.onlineCounts = onlineCounts;
    }

    public String getCountType() {
        return countType;
    }

    public void setCountType(String countType) {
        this.countType = countType;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
