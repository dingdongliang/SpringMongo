package net.htjs.sendsys.interceptor;

import net.htjs.sendsys.model.ProLog;
import net.htjs.sendsys.mongo.BlackList;
import net.htjs.sendsys.mongo.IpInfo;
import net.htjs.sendsys.service.BlackListService;
import net.htjs.sendsys.service.IpCheckService;
import net.htjs.sendsys.service.LogService;
import net.htjs.sendsys.utils.DateUtil;
import net.htjs.sendsys.utils.IpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ResourceBundle;

/**
 * Description: IP地址拦截器
 * author  dyenigma
 * date 2016/9/21 10:45
 */
public class IpInterceptor implements HandlerInterceptor {

    @Autowired
    private IpCheckService ipCheckService;
    @Autowired
    private BlackListService blackListService;
    @Autowired
    private LogService logService;

    /**
     * Description:  Controller处理之前进行调用，IP地址过滤
     * 步骤：先查询黑名单，然后看黑名单中的警告次数，如果超过设定次数拒绝访问
     * methodName:preHandle
     * Time:2016/9/23 10:10
     * param:[request, response, handler]
     * return:boolean
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws
            Exception {

        String ip = IpUtil.getIpAddr(request);
        ResourceBundle bundle = ResourceBundle.getBundle("config");

        //查询黑名单,如果黑名单中超过一定次数就返回true,就需要中断请求
        BlackList blackList = blackListService.searchByIp(ip, DateUtil.onlyDate());
        if (blackList != null && blackList.getCount() >= Integer.parseInt(bundle.getString("ip.visit.warning"))) {
            return false;
        }

        //查询最近访问时间，与设置频率比较
        IpInfo ipInfo = ipCheckService.getLastTime(ip);
        if (ipInfo != null) {
            long currentTime = System.currentTimeMillis();
            long lastTime = DateUtil.str2Date(ipInfo.getRequestTime()).getTime();


            long frequency = Long.parseLong(bundle.getString("ip.visit.frequency"));

            //更新访问时间和次数
            ipInfo.setRequestTime(DateUtil.dateAndTime());
            ipInfo.setRequestCount(ipInfo.getRequestCount() + 1);
            ipCheckService.update(ipInfo);

            //如果超过设定频率，黑名单警告次数累计
            if ((currentTime - lastTime) <= frequency) {
                if (blackList != null) {
                    blackList.setCount(blackList.getCount() + 1);
                    blackListService.update(blackList);
                } else {
                    //每天首次加入黑名单
                    blackList = new BlackList();
                    blackList.setIp(ip);
                    blackList.setTime(DateUtil.onlyDate());
                    blackList.setCount(1);
                    blackListService.insert(blackList);
                }

                ProLog log = new ProLog();
                log.setRequestIp(ip);
                log.setMethod("黑名单");
                log.setCreateTime(DateUtil.dateAndTime());
                log.setDescription("超过访问频率，IP加入黑名单");
                logService.add(log);
            }
        } else {
            //每天的第一次访问，插入新纪录
            ipInfo = new IpInfo();
            ipInfo.setIp(ip);
            ipInfo.setRequestTime(DateUtil.dateAndTime());
            ipInfo.setRequestCount(1);
            ipCheckService.insert(ipInfo);
        }

        return true;
    }

    /**
     * Description: Controller的方法调用之后执行，但是它会在DispatcherServlet进行视图的渲染之前执行
     * 也就是说在这个方法中你可以对ModelAndView进行操作。顺序参考上面的preHandle，但注意顺序相反
     * 用一条直线贯穿一系列同心圆模式理解
     * methodName:postHandle
     * Time:2016/9/23 10:09
     * param:[request, response, handler, modelAndView]
     * return:void
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView
            modelAndView) throws Exception {
    }


    /**
     * Description: 该方法将在整个请求完成之后，也就是DispatcherServlet渲染了视图执行,主要用于清理资源
     * methodName:afterCompletion
     * Time:2016/9/23 10:09
     * param:[request, response, handler, ex]
     * return:void
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception
            ex) throws Exception {
    }
}
