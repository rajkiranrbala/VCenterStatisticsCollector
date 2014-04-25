package edu.cmpe283.prj2.model.util;

import java.util.Date;


public class ChartData {

    private Date dateTime;
    private String hostName;
   
    private double value;
   
   

    
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


	public double getValue() {
		return value;
	}


	public void setValue(double value) {
		this.value = value;
	}


	

}
