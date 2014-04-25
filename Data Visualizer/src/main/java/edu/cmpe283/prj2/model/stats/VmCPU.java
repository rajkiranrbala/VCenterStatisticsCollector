package edu.cmpe283.prj2.model.stats;

import java.util.Date;


public class VmCPU {

    private Date dateTime;
    private String hostName;
    private Double cPUUsage;
    private int rollupStamp;

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public Double getcPUUsage() {
        return cPUUsage;
    }

    public void setcPUUsage(Double cPUUsage) {
        this.cPUUsage = cPUUsage;
    }

    public int getRollupStamp() {
        return rollupStamp;
    }

    public void setRollupStamp(int rollupStamp) {
        this.rollupStamp = rollupStamp;
    }

    @Override
    public String toString() {
        return "VmCPU{" +
                "dateTime=" + dateTime +
                ", hostName='" + hostName + '\'' +
                ", cPUUsage=" + cPUUsage +
                ", rollupStamp=" + rollupStamp +
                '}';
    }
}
