package edu.cmpe283.prj2.model.chart;

import java.util.List;

/**
 * User: vplociennik
 * Date: 12/1/13
 * Time: 1:43 AM
 */
public class Data {

    private String name;
    private List<String> data;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}
