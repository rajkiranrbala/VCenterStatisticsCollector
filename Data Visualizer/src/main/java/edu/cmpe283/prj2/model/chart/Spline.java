package edu.cmpe283.prj2.model.chart;

/**
 * User: vplociennik
 * Date: 12/2/13
 * Time: 3:27 AM
 */
public class Spline {

    private Marker marker;

    public Spline() {
        this.marker = new Marker();
    }

    public Marker getMarker() {
        return marker;
    }

    public void setMarker(Marker marker) {
        this.marker = marker;
    }
}
