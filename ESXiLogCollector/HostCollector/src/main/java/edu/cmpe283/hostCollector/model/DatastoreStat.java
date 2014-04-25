package edu.cmpe283.hostCollector.model;

import com.vmware.vim25.mo.Datastore;
import java.util.Date;
import java.util.List;

/**
 * User: vplociennik
 * Date: 11/22/13
 * Time: 2:42 AM
 */
public class DatastoreStat {

    private String datastoreName;
    private Date timestamp;
    private long diskSize;
    private long freespace;
    private long diskPercentage;
    private String rio;
    private String wio;


    public DatastoreStat(Datastore datastore) {
        diskSize = datastore.getSummary().capacity;
        freespace = datastore.getSummary().freeSpace;
        timestamp = new Date();
        datastoreName = datastore.getName();
        diskPercentage = (diskSize - freespace)*100/diskSize;
    }

    public long getDiskSize() {
        return diskSize;
    }

    public void setDiskSize(long diskSize) {
        this.diskSize = diskSize;
    }

    public long getFreespace() {
        return freespace;
    }

    public void setFreespace(long freespace) {
        this.freespace = freespace;
    }

    public String getDatastoreName() {
        return datastoreName;
    }

    public void setDatastoreName(String datastoreName) {
        this.datastoreName = datastoreName;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public long getDiskPercentage() {
        return diskPercentage;
    }

    public void setDiskPercentage(long diskPercentage) {
        this.diskPercentage = diskPercentage;
    }

    public void setIO(List<String> strings) {
        wio = strings.get(0);
        rio = strings.get(2);
    }

    public String getRio() {
        return rio;
    }

    public String getWio() {
        return wio;
    }

    @Override
    public String toString() {
        return "DatastoreStat{" +
                "datastoreName='" + datastoreName + '\'' +
                ", timestamp=" + timestamp +
                ", diskSize=" + diskSize +
                ", freespace=" + freespace +
                ", diskPercentage=" + diskPercentage +
                ", rio='" + rio + '\'' +
                ", wio='" + wio + '\'' +
                '}';
    }
}
