package edu.cmpe283.hostCollector.model;

import java.util.List;

/**
 * User: vplociennik
 * Date: 11/28/13
 * Time: 3:15 AM
 */
public class ConfigFile {

    private String vcenterUrl;
    private String vcenterUserName;
    private String vcenterPassword;
    private List<String> hosts;
    private String logFolder;
    private Integer sampleTime;

    public String getVcenterUrl() {
        return vcenterUrl;
    }

    public void setVcenterUrl(String vcenterUrl) {
        this.vcenterUrl = vcenterUrl;
    }

    public String getVcenterUserName() {
        return vcenterUserName;
    }

    public void setVcenterUserName(String vcenterUserName) {
        this.vcenterUserName = vcenterUserName;
    }

    public String getVcenterPassword() {
        return vcenterPassword;
    }

    public void setVcenterPassword(String vcenterPassword) {
        this.vcenterPassword = vcenterPassword;
    }

    public List<String> getHosts() {
        return hosts;
    }

    public void setHosts(List<String> hosts) {
        this.hosts = hosts;
    }

    public String getLogFolder() {
        return logFolder;
    }

    public void setLogFolder(String logFolder) {
        this.logFolder = logFolder;
    }

    public Integer getSampleTime() {
        return sampleTime;
    }

    public void setSampleTime(Integer sampleTime) {
        this.sampleTime = sampleTime;
    }

    @Override
    public String toString() {
        return "ConfigFile{" +
                "vcenterUrl='" + vcenterUrl + '\'' +
                ", vcenterUserName='" + vcenterUserName + '\'' +
                ", vcenterPassword='" + vcenterPassword + '\'' +
                ", hosts=" + hosts +
                ", logFolder='" + logFolder + '\'' +
                ", sampleTime=" + sampleTime +
                '}';
    }
}
