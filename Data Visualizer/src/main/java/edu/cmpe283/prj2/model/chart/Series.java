package edu.cmpe283.prj2.model.chart;

import java.util.List;

/**
 * User: vplociennik
 * Date: 12/1/13
 * Time: 1:35 AM
 */
public class Series {
    private String name;
    private List<Double> data;

    public Series(String name, List<Double> data) {
        this.name = name;
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Double> getData() {
        return data;
    }

    public void setData(List<Double> data) {
        this.data = data;
    }
}
