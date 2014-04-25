package edu.cmpe283.hostCollector.model;

import java.util.Date;
import java.util.List;

/**
 * User: vplociennik
 * Date: 11/21/13
 * Time: 4:22 AM
 */
public class HostStat {

    private String hostname;
    private Date timestamp;
    private Long cpuusage;
    private Long cpumax;
    private Long cpupercentage;
    private Long memusage;
    private Long memmax;
    private Long mempercentage;
    private Long uptime;
    private Long tx;
    private Long rx;




    public HostStat() {
        timestamp = new Date();
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Long getCpuusage() {
        return cpuusage;
    }

    public void setCpuusage(Long cpuusage) {
        this.cpuusage = cpuusage;
    }

    public Long getCpumax() {
        return cpumax;
    }

    public void setCpumax(Long cpumax) {
        this.cpumax = cpumax;
    }

    public Long getMemusage() {
        return memusage;
    }

    public void setMemusage(Long memusage) {
        this.memusage = memusage;
    }

    public Long getMemmax() {
        return memmax;
    }

    public void setMemmax(Long memmax) {
        this.memmax = memmax;
    }

    public Long getUptime() {
        return uptime;
    }

    public void setUptime(Long uptime) {
        this.uptime = uptime;
    }

    public Long getCpupercentage() {
        return cpupercentage;
    }

    public void setCpupercentage(Long cpupercentage) {
        this.cpupercentage = cpupercentage;
    }

    public Long getMempercentage() {
        return mempercentage;
    }

    public void setMempercentage(Long mempercentage) {
        this.mempercentage = mempercentage;
    }

    public Long getTx() {
        return tx;
    }

    public void setTx(Long tx) {
        this.tx = tx;
    }

    public Long getRx() {
        return rx;
    }

    public void setRx(Long rx) {
        this.rx = rx;
    }

    public void setNet(List<String> strings) {
        rx = Long.parseLong(strings.get(0));
        tx = Long.parseLong(strings.get(2));
    }
}
