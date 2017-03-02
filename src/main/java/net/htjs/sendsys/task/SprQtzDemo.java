package net.htjs.sendsys.task;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * Description:定时器实现类
 * author  dyenigma
 * date 2016/9/12 16:42
 */
public class SprQtzDemo extends QuartzJobBean {

    private final Logger LOGGER = LoggerFactory.getLogger(SprQtzDemo.class);

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        LOGGER.debug("执行定时任务演示，定时任务已经执行，结束");
    }

}
