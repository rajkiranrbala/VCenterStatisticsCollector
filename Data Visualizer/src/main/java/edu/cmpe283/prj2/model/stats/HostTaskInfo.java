package edu.cmpe283.prj2.model.stats;

import java.util.Date;

public class HostTaskInfo {
	private Date dateTime;
	private String targetVM;
	private String action;
	private Double timeTaken;
	private int  rollupStamp;
	public Date getDateTime() {
		return dateTime;
	}
	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}
	public String getTargetVM() {
		return targetVM;
	}
	public void setTargetVM(String targetVM) {
		this.targetVM = targetVM;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public Double getTimeTaken() {
		return timeTaken;
	}
	public void setTimeTaken(Double timeTaken) {
		this.timeTaken = timeTaken;
	}
	public int getRollupStamp() {
		return rollupStamp;
	}
	public void setRollupStamp(int rollupStamp) {
		this.rollupStamp = rollupStamp;
	}
	
	
}
