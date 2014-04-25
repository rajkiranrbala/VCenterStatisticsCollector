package edu.cmpe283.prj2.model.chart;

import edu.cmpe283.prj2.dao.InventoryDao;
import edu.cmpe283.prj2.model.util.Str;

/**
 * User: vplociennik
 * Date: 12/2/13
 * Time: 2:54 AM
 */
public class Selector {

    private Integer selected;

    public Selector() {
        this.selected = 1;
    }

    public Integer getSelected() {
        return selected;
    }

    public void setSelected(Integer selected) {
        this.selected = selected;
    }
}
