package edu.cmpe283.prj2.model.chart;

/**
 * User: vplociennik
 * Date: 12/1/13
 * Time: 1:06 AM
 */
public class Chart {

    private String type;

    public Chart() {
        this.type = "spline";
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
