package edu.cmpe283.prj2.model.chart;

/**
 * User: vplociennik
 * Date: 12/2/13
 * Time: 3:27 AM
 */
public class Marker {

    private boolean enabled;

    public Marker() {
        this.enabled = false;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
