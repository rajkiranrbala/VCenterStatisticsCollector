package edu.cmpe283.prj2.model.chart;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * User: vplociennik
 * Date: 12/1/13
 * Time: 1:34 AM
 */
public class XAxis {

    private List<String> categories;
    private Labels labels;

    public XAxis(List<Date> c) {
        categories = new ArrayList<String>();

        SimpleDateFormat dt = new SimpleDateFormat("hh:mm");
        for (Date date : c) {
            categories.add(dt.format(date));
        }
        labels = new Labels();

    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public Labels getLabels() {
        return labels;
    }

    public void setLabels(Labels labels) {
        this.labels = labels;
    }

    public void setStep(int rollupStamp) {
        labels = new Labels(rollupStamp);
    }
}
