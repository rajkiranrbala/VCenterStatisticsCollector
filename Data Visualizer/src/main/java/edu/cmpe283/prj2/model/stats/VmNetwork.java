package edu.cmpe283.prj2.model.stats;

import java.util.Date;

/**
 * User: vplociennik
 * Date: 11/27/13
 * Time: 2:27 AM
 * 
 * 
 * 
 */
public class VmNetwork {

    private Date dateTime;
    private String hostName;
    private String Interface ;
    private double Tx;
    private double Rx ;
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


	public String getInterface() {
		return Interface;
	}


	public void setInterface(String interface1) {
		Interface = interface1;
	}


	public double getTx() {
		return Tx;
	}


	public void setTx(double tx) {
		Tx = tx;
	}


	public double getRx() {
		return Rx;
	}


	public void setRx(double rx) {
		Rx = rx;
	}


	public int getRollupStamp() {
		return rollupStamp;
	}


	public void setRollupStamp(int rollupStamp) {
		this.rollupStamp = rollupStamp;
	}


	@Override
    public String toString() {
        return "VmNetworkTx{" +
                "dateTime=" + dateTime +
                ", hostName=" + hostName +  
                ", Rx=" + Rx  +  ",Tx ="  + Tx  + 
                ", rollupStamp=" + rollupStamp +
                '}';
    }
}
