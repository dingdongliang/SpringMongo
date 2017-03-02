package net.htjs.sendsys.test.mongodb;

import net.htjs.sendsys.mongo.IpAddr;
import net.htjs.sendsys.utils.AddrUtil;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * author  dyenigma
 * date 2016/10/18 15:01
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring.xml", "classpath:spring-mvc.xml", "classpath:spring-mongodb.xml"})
public class InsertIpAddr {
    @Autowired
    MongoTemplate mongoEventsTemplate;

    @BeforeClass
    public static void init() {

    }

    @Test
    public void insert() {
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("D://ip.txt"), "GB2312"));
            String readStr;
            List<IpAddr> ipList = new ArrayList<>();
            while ((readStr = bufferedReader.readLine()) != null) {
                String[] strs = readStr.split("\\|");
                IpAddr ipAddr = new IpAddr();
                ipAddr.setIpStart(strs[0]);
                ipAddr.setIpEnd(strs[1]);
                ipAddr.setIpGt(AddrUtil.ipToLong(strs[0]));
                ipAddr.setIpLt(AddrUtil.ipToLong(strs[1]));
                ipAddr.setAddr(strs[2]);
                if (strs.length < 4) {
                    ipAddr.setCompany(null);
                } else {
                    ipAddr.setCompany("CZ88.NET".equals(strs[3]) ? null : strs[3]);
                }
                ipList.add(ipAddr);
                if (ipList.size() > 5000) {
                    mongoEventsTemplate.insertAll(ipList);
                    ipList = new ArrayList<>();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedReader != null)
                    bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
