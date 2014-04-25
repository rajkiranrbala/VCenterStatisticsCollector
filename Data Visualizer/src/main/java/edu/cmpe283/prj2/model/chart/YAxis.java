package edu.cmpe283.prj2.model.chart;

/**
 * User: vplociennik
 * Date: 12/1/13
 * Time: 1:34 AM
 */
public class YAxis {

    private Title title;

    public YAxis(String title) {
        this.title = new Title(title);
    }

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }
}
