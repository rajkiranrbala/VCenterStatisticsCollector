package edu.cmpe283.prj2.model.stats;

import java.util.Date;

public class HostInfo {
	private Date dateTime;
	private String hostname;
	private Double cpuUSage ;
	private Double cpuMax;
	private Double cpuPercentage;
	private Double memUsage;
	private Double memMax;
	private Double memPercentage;
	private Double uptime;
	private Double tx;
	private Double rx;
	private int  rollupStamp;
	public Date getDateTime() {
		return dateTime;
	}
	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}
	public String getHostname() {
		return hostname;
	}
	public void setHostname(String hostname) {
		this.hostname = hostname;
	}
	public Double getCpuUSage() {
		return cpuUSage;
	}
	public void setCpuUSage(Double cpuUSage) {
		this.cpuUSage = cpuUSage;
	}
	public Double getCpuMax() {
		return cpuMax;
	}
	public void setCpuMax(Double cpuMax) {
		this.cpuMax = cpuMax;
	}
	public Double getCpuPercentage() {
		return cpuPercentage;
	}
	public void setCpuPercentage(Double cpuPercentage) {
		this.cpuPercentage = cpuPercentage;
	}
	public Double getMemUsage() {
		return memUsage;
	}
	public void setMemUsage(Double memUsage) {
		this.memUsage = memUsage;
	}
	public Double getMemMax() {
		return memMax;
	}
	public void setMemMax(Double memMax) {
		this.memMax = memMax;
	}
	public Double getMemPercentage() {
		return memPercentage;
	}
	public void setMemPercentage(Double memPercentage) {
		this.memPercentage = memPercentage;
	}
	public Double getUptime() {
		return uptime;
	}
	public void setUptime(Double uptime) {
		this.uptime = uptime;
	}
	public Double getTx() {
		return tx;
	}
	public void setTx(Double tx) {
		this.tx = tx;
	}
	public Double getRx() {
		return rx;
	}
	public void setRx(Double rx) {
		this.rx = rx;
	}
	public int getRollupStamp() {
		return rollupStamp;
	}
	public void setRollupStamp(int rollupStamp) {
		this.rollupStamp = rollupStamp;
	}
	
	
	
	
}
