package edu.cmpe283.prj2.model.chart;

/**
 * User: vplociennik
 * Date: 12/1/13
 * Time: 1:35 AM
 */
public class PlotOptions {

    private Spline spline;

    public PlotOptions() {
        this.spline = new Spline();
    }

    public Spline getSpline() {
        return spline;
    }

    public void setSpline(Spline spline) {
        this.spline = spline;
    }
}

