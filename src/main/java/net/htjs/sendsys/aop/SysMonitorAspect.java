package net.htjs.sendsys.aop;

import com.alibaba.fastjson.JSON;
import net.htjs.sendsys.mongo.Monitor;
import net.htjs.sendsys.service.MonitorService;
import net.htjs.sendsys.utils.DateUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Description:监控切面，用于计算某个方法的调用时间
 * author  dyenigma
 * date 2016/10/11 11:03
 */
@Aspect
@Component
@Order(1)
public class SysMonitorAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(SysMonitorAspect.class);

    @Autowired
    private MonitorService monitorService;

    @Pointcut("@annotation(net.htjs.sendsys.annotation.LogRecord)")
    public void moniAspect() {

    }

    //环绕通知
    @Around("moniAspect()")
    public Object doAround(ProceedingJoinPoint jointPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = jointPoint.proceed();
        long end = System.currentTimeMillis();

        Monitor monitor = new Monitor();
        monitor.setClassName(jointPoint.getTarget().getClass().getName());
        monitor.setMethodName(jointPoint.getSignature().getName());
        Object[] args = jointPoint.getArgs(); //返回被通知方法的参数列表,而不是POST|GET传递过来的参数
        Map<String, String> paramMap = new HashMap<>();
        if (args != null && args.length > 0 && args[0] instanceof HttpServletRequest) {
            HttpServletRequest request = (HttpServletRequest) args[0];
            Map params = request.getParameterMap();
            if ((params != null) && (params.size() > 0)) {
                Iterator iter = params.entrySet().iterator();
                String[] valueHolder = new String[1];
                while (iter.hasNext()) {
                    Map.Entry entry = (Map.Entry) iter.next();
                    String name = (String) entry.getKey();

                    Object value = entry.getValue();
                    String[] values;
                    if (value instanceof String[]) {
                        values = (String[]) value;
                    } else {
                        valueHolder[0] = value.toString();
                        values = valueHolder;
                    }

                    for (String value1 : values) {
                        if (value1 != null) {
                            paramMap.put(name, value1);
                        }
                    }
                }
            }
        }
        monitor.setParamLists(JSON.toJSONString(paramMap));
        monitor.setCreateTime(DateUtil.dateAndTime());

        monitor.setAllTime(end - start);

        LOGGER.info(monitor.toString());

        monitorService.saveMonitorInfo(monitor);

        return result;
    }

}
