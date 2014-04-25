package edu.cmpe283.prj2.model.chart;

import edu.cmpe283.prj2.manager.InventoryManager;

/**
 * User: vplociennik
 * Date: 12/1/13
 * Time: 7:00 PM
 */
public class Labels {

    private Integer step;

    public Labels() {
        step = 6;
    }

    public Labels(int rollupStamp) {
        if(rollupStamp == 1)
            step = 6;
        else if (rollupStamp == 2)
            step = 3;
        else
            step = 1;
    }

    public Integer getStep() {
        return step;
    }

    public void setStep(Integer step) {
        this.step = step;
    }
}
