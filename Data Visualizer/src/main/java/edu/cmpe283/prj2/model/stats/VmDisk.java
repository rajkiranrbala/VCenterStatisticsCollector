package edu.cmpe283.prj2.model.stats;



import java.util.Date;

public class VmDisk {
	 private Date dateTime;
	    private String hostName;
	    private String diskID;
	    private Double writeIO;
	    private Double readIO;
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

    public String getDiskID() {
        return diskID;
    }

    public void setDiskID(String diskID) {
        this.diskID = diskID;
    }

    public Double getReadIO() {
        return readIO;
    }

    public void setReadIO(Double readIO) {
        this.readIO = readIO;
    }

    public Double getWriteIO() {
        return writeIO;
    }

    public void setWriteIO(Double writeIO) {
        this.writeIO = writeIO;
    }

    public int getRollupStamp() {
        return rollupStamp;
    }

    public void setRollupStamp(int rollupStamp) {
        this.rollupStamp = rollupStamp;
    }
}

