package net.htjs.sendsys.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import net.htjs.sendsys.annotation.GetParams;
import net.htjs.sendsys.annotation.LogRecord;
import net.htjs.sendsys.model.EchartsData;
import net.htjs.sendsys.model.MapData;
import net.htjs.sendsys.model.Series;
import net.htjs.sendsys.mongo.OnlineCounts;
import net.htjs.sendsys.service.OnlineCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/27.
 */
@Controller
public class OnlineCountController {
    @Autowired
    private OnlineCountService onlineCountService;

    @ResponseBody
    @RequestMapping(value = "/getHourCount", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @GetParams
    @LogRecord(description = "按小时查询在线用户变化曲线")
    public String getHourCount(String project, String day) {
        List<String> xaxis = new ArrayList<String>();
        Series series = new Series();
        series.setName("用户量");
        series.setType("line");
        List<Integer> data = new ArrayList<Integer>();
        List<OnlineCounts> ocs = onlineCountService.getCountByHour(project, day);
        for (OnlineCounts oc : ocs) {
            xaxis.add(oc.getCountTime().substring(11));
            data.add(Integer.parseInt(oc.getOnlineCounts().toString()));
        }
        series.setData(data);
        EchartsData echartdata = new EchartsData();
        echartdata.setxAxis(xaxis);
        echartdata.setSeries(series);
        return JSON.toJSONString(echartdata);
    }

    @ResponseBody
    @RequestMapping(value = "/getDayCount", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @GetParams
    @LogRecord(description = "按天查询在线用户变化曲线")
    public String getDayCount(String project, String month) {
        List<String> xaxis = new ArrayList<String>();
        Series series = new Series();
        series.setName("用户量");
        series.setType("line");
        List<Integer> data = new ArrayList<Integer>();
        List<OnlineCounts> ocs = onlineCountService.getCountByDay(project, month);
        for (OnlineCounts oc : ocs) {
            xaxis.add(oc.getCountTime().substring(8, 10));
            data.add(oc.getOnlineCounts());
        }
        series.setData(data);
        EchartsData echartdata = new EchartsData();
        echartdata.setxAxis(xaxis);
        echartdata.setSeries(series);
        return JSON.toJSONString(echartdata);
    }

    @ResponseBody
    @RequestMapping(value = "/getCityDayCount", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @GetParams
    @LogRecord(description = "按天分区域查询在线用户变化曲线")
    public String getCityDayCount(String project, String day) {
        List<OnlineCounts> ocs = onlineCountService.getCityCountByDay(project, day);
        List<MapData> mdlist = new ArrayList<MapData>();
        for(OnlineCounts oc :ocs){
            MapData md =new MapData();
            md.setName(oc.getCity());
            md.setValue(oc.getOnlineCounts());
            mdlist.add(md);
        }
        return JSONArray.toJSONString(mdlist);
    }

    @ResponseBody
    @RequestMapping(value = "/getMinutesCount", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @GetParams
    @LogRecord(description = "按分钟查询在线用户变化曲线")
    public String getMinutesCount(String project) {
        OnlineCounts oc = onlineCountService.getCountByMinute(project);
        oc.setCountTime(oc.getCountTime().substring(5));
        return JSON.toJSONString(oc);
    }

    @ResponseBody
    @RequestMapping(value = "/getOneDayMinutesCount", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @GetParams
    @LogRecord(description = "查询24小时内在线用户变化曲线")
    public String getOneDayMinutesCount(String project) {
        List<String> xaxis = new ArrayList<String>();
        Series series = new Series();
        series.setName("用户量");
        series.setType("line");
        List<Integer> data = new ArrayList<Integer>();
        List<OnlineCounts> ocs = onlineCountService.getCountByMinutes(project);
        for (OnlineCounts oc : ocs) {
            xaxis.add(oc.getCountTime().substring(5));
            data.add(oc.getOnlineCounts());
        }
        series.setData(data);
        EchartsData echartdata = new EchartsData();
        echartdata.setxAxis(xaxis);
        echartdata.setSeries(series);
        return JSON.toJSONString(echartdata);
    }
}
