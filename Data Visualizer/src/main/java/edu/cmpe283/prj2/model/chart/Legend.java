package edu.cmpe283.prj2.model.chart;

/**
 * User: vplociennik
 * Date: 12/1/13
 * Time: 3:01 AM
 */
public class Legend {



    private  String layout = "vertical";
    private String align = "right";
    private String verticalAlign = "middle";
    private Integer borderWidth = 0;

    public String getLayout() {
        return layout;
    }

    public void setLayout(String layout) {
        this.layout = layout;
    }

    public String getAlign() {
        return align;
    }

    public void setAlign(String align) {
        this.align = align;
    }

    public String getVerticalAlign() {
        return verticalAlign;
    }

    public void setVerticalAlign(String verticalAlign) {
        this.verticalAlign = verticalAlign;
    }

    public Integer getBorderWidth() {
        return borderWidth;
    }

    public void setBorderWidth(Integer borderWidth) {
        this.borderWidth = borderWidth;
    }
}
