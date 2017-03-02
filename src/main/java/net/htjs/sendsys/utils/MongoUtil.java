package net.htjs.sendsys.utils;

/**
 * Description: 芒果DB工具类,用于将实体类转换成DBObject
 * author  dyenigma
 * date 2016/9/13 10:44
 */
public class MongoUtil {

    public static final String COLLECTION = "testdb"; //测试库
    public static final String LOG = "logdb"; //日志库
    public static final String MSG = "msgdb";//消息池库
    public static final String RULE = "ruledb";//规则库
    public static final String BLACKLIST = "blacklistdb";//黑名单库
    public static final String INFO = "infodb";//用户信息库

    public static final String IPVISIT = "ipdb"; //IP地址访问统计库

    public static final String MONITOR = "monitordb"; //监控日志库

    public static final String IPADDR = "ipAddrdb";//IP地址库

    public static final String BIGDATA = "bigData"; //海量数据插入

    private MongoUtil() {
    }

}
