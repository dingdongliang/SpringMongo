package net.htjs.sendsys.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Description: 日期处理类
 * author  dyenigma
 * date 2016/9/18 16:15
 */
public class DateUtil {


    private static final Logger LOGGER = LoggerFactory.getLogger(DateUtil.class);

    public static final String DATETIME = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE = "yyyy-MM-dd";
    public static final String TIME = "HH:mm:ss";

    private DateUtil() {
    }

    // HH:mm:ss格式化时间
    public static String onlyTime() {
        return new SimpleDateFormat(TIME).format(new Date());
    }

    // yyyy-MM-dd格式化日期
    public static String onlyDate() {
        return new SimpleDateFormat(DATE).format(new Date());
    }

    // yyyy-MM-dd HH:mm:ss格式化日期时间
    public static String dateAndTime() {
        return dateAndTime(DATETIME);
    }

    // 自定义格式化时间字符串样式
    public static String dateAndTime(String format) {
        return new SimpleDateFormat(format).format(new Date());
    }


    public static Date str2Date(String str) {
        return str2Date("yyyy-MM-dd HH:mm:ss", str);
    }

    // 字符串转换成日期类型
    public static Date str2Date(String format, String str) {
        Date date = null;
        try {
            date = new SimpleDateFormat(format).parse(str);
        } catch (ParseException e) {
            LOGGER.info("日期转换错误,ParseException:{}", e.getMessage());
        }
        return date;
    }

    /**
     * Description: 获取当前月的英文缩写
     * methodName:getMonth
     * Time:2016/9/23 10:17
     * param:[]
     * return:java.lang.String
     */
    public static String getMonth() {
        return getMonth(new Date());
    }

    /**
     * Description: 获取某个日期所在的月份缩写,MM为01类型,MMM为缩写类型，MMMM为全部单词
     * Time:2016/9/23 10:02
     * param:[date]
     * return:java.lang.String
     */
    public static String getMonth(Date date) {

        DateFormat df = new SimpleDateFormat("MMMM", Locale.ENGLISH);
        return df.format(date);
    }

    public static String date2Str(String format, Date date) {
        return new SimpleDateFormat(format).format(date);
    }

    /**
     * Description: 获取时间戳
     * methodName:getTimeStamp
     * Time:2016/9/23 10:04
     * param:[]
     * return:java.sql.Timestamp
     */
    public static Timestamp getTimeStamp() {

        return new Timestamp(new Date().getTime());
    }

    /**
     * Description: 当前日期相差一定小时数的日期
     * methodName:getSpecifiedDay
     * Time:2016/9/23 10:04
     * param:[hours]
     * return:java.lang.String
     */
    public static String getSpecifiedDay(int hours) {

        String now = new SimpleDateFormat(DATETIME).format(new Date());
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            date = new SimpleDateFormat(DATETIME).parse(now);
        } catch (ParseException e) {
            LOGGER.info("日期转换错误,ParseException:{}", e.getMessage());
        }
        c.setTime(date);
        int hour = c.get(Calendar.HOUR);
        c.set(Calendar.HOUR, hour + hours);

        return new SimpleDateFormat(DATETIME).format(c.getTime());
    }
}
