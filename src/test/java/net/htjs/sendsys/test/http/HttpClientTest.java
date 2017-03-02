package net.htjs.sendsys.test.http;

import net.htjs.sendsys.utils.HttpClientUtil;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 * author  dyenigma
 * date 2016/9/26 11:47
 */
public class HttpClientTest {

    private final String HTTPURL = "http://10.3.105.5:8181";

    @Test
    public void sendPost() {
        Map<String, String> maps = new HashMap<>();
        maps.put("Project", "32f222223");
        maps.put("ClientType", "Chrome");
        maps.put("OtherData", "100");
        maps.put("UserId", "14000191");
        maps.put("ClientId", "8edfdfa75c104c22aa0a613b9e6ef4da");
        maps.put("ClientVersion", "46.0.1");
        maps.put("KeyFlag", "1");
        String responseContent = HttpClientUtil.getInstance()
                .sendHttpPost(HTTPURL + "/user/save", maps);
        System.out.println("reponse content:" +responseContent);
    }


}