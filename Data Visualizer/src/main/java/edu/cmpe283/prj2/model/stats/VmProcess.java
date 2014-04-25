package edu.cmpe283.prj2.model.stats;

import java.util.Date;



public class VmProcess {

	
	
    private Date dateTime;
    private String hostName;
   
    private int process;
    private int threads ;
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


    public int getThreads() {
        return threads;
    }

    public void setThreads(int threads) {
        this.threads = threads;
    }

    public int getProcess() {
        return process;
    }

    public void setProcess(int process) {
        this.process = process;
    }

    public int getRollupStamp() {
		return rollupStamp;
	}



	public void setRollupStamp(int rollupStamp) {
		this.rollupStamp = rollupStamp;
	}




   
}
