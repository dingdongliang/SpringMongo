package net.htjs.sendsys.mongo;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Description:
 * author  dyenigma
 * date 2016/10/20 11:53
 */
@Document(collection = "bigData")
public class BigData {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
