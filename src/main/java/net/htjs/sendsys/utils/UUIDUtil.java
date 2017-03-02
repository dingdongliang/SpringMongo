package net.htjs.sendsys.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Description: 自动生成一个32位的UUID
 * author  dyenigma
 * date 2016/9/12 18:33
 */
public class UUIDUtil {
    private UUIDUtil() {
    }

    /**
     * Description: 获得一个UUID
     * methodName:getUUID
     * Time:2016/9/23 10:21
     * param:[]
     * return:java.lang.String
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    //获得指定数量的UUID
    public static List<String> getUUID(int number) {
        if (number < 1) {
            return new ArrayList<>();
        }
        List<String> strList = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            strList.add(getUUID());
        }
        return strList;
    }
}
