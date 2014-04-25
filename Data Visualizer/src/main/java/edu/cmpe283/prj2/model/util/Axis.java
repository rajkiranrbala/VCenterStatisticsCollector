package edu.cmpe283.prj2.model.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * User: vplociennik
 * Date: 11/27/13
 * Time: 3:49 AM
 */
public class Axis {

    private List<Date> x;
    private String y;

    public Axis(List<Date> x, String y) {
        this.x = x;
        this.y = y;
    }

    public String getX() {
        String s = "[";

        for (Date s1 : x) {
            s += "'"+s1+"',";
        }

        s = s.substring(0, s.length()-1) + "]";

        return s;
    }

    public String getY() {
        return y;
    }
}
