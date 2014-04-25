package edu.cmpe283.prj2.model.chart;

import java.util.List;

/**
 * User: vplociennik
 * Date: 12/1/13
 * Time: 1:05 AM
 */
public class ChartStructure {

    private Chart chart;
    private Title title;
    private Subtitle subtitle;
    private XAxis xAxis;
    private YAxis yAxis;
    private List<Series> series;
    private Legend legend;
    private PlotOptions plotOptions;
    private int rollupStamp;
  //  private Selector rangeSelector;


   /* public ChartStructure(String title, String subtitle, List<Date> xAxis, String yAxis, List<Series> series) {
        this.chart = new Chart();
        this.title = new Title(title);
        this.subtitle = new Subtitle(subtitle);
        this.xAxis = new XAxis(xAxis);
        this.yAxis = new YAxis(yAxis);
        this.series = series;
        this.legend = new Legend();
    }*/

    public ChartStructure(String title, String subtitle, String yAxis, int rollupStamp) {
        this.chart = new Chart();
        this.title = new Title(title);
        this.subtitle = new Subtitle(subtitle);
        this.yAxis = new YAxis(yAxis);
        this.legend = new Legend();
        this.plotOptions = new PlotOptions();
        this.rollupStamp = rollupStamp;
  //      this.rangeSelector = new Selector();
    }


    public Chart getChart() {
        return chart;
    }

    public void setChart(Chart chart) {
        this.chart = chart;
    }

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public Subtitle getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(Subtitle subtitle) {
        this.subtitle = subtitle;
    }

    public XAxis getxAxis() {
        return xAxis;
    }

    public void setxAxis(XAxis xAxis) {
        xAxis.setStep(rollupStamp);
        this.xAxis = xAxis;
    }

    public YAxis getyAxis() {
        return yAxis;
    }

    public void setyAxis(YAxis yAxis) {
        this.yAxis = yAxis;
    }

    public List<Series> getSeries() {
        return series;
    }

    public void setSeries(List<Series> series) {
        this.series = series;
    }

    public Legend getLegend() {
        return legend;
    }

    public void setLegend(Legend legend) {
        this.legend = legend;
    }

    public PlotOptions getPlotOptions() {
        return plotOptions;
    }

    public void setPlotOptions(PlotOptions plotOptions) {
        this.plotOptions = plotOptions;
    }
}
