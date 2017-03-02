package net.htjs.sendsys.model;

import java.util.List;

/**
 * Created by Administrator on 2016/9/27.
 */
public class EchartsData {
    private List<String> xAxis;
    private List<String> yAxis;
    private List<String> legend;
    private Series series;

    public List<String> getxAxis() {
        return xAxis;
    }

    public void setxAxis(List<String> xAxis) {
        this.xAxis = xAxis;
    }

    public List<String> getyAxis() {
        return yAxis;
    }

    public void setyAxis(List<String> yAxis) {
        this.yAxis = yAxis;
    }

    public List<String> getLegend() {
        return legend;
    }

    public void setLegend(List<String> legend) {
        this.legend = legend;
    }

    public Series getSeries() {
        return series;
    }

    public void setSeries(Series series) {
        this.series = series;
    }
}
