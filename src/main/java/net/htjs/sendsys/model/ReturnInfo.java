package net.htjs.sendsys.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Description: 返回值封装类
 * author  dyenigma
 * date 2016/9/22 11:43
 */
public class ReturnInfo {
    private String code;
    private Object date;

    public ReturnInfo(String code, Object date) {
        this.code = code;
        this.date = date;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getDate() {
        return date;
    }

    public void setDate(Object date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
