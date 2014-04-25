package edu.cmpe283.prj2.dao;

import edu.cmpe283.prj2.model.stats.HostInfo;
import edu.cmpe283.prj2.model.stats.VmCPU;
import edu.cmpe283.prj2.model.stats.VmDisk;
import edu.cmpe283.prj2.model.stats.VmMemory;
import edu.cmpe283.prj2.model.stats.VmNetwork;
import edu.cmpe283.prj2.model.stats.VmProcess;

import java.util.List;

/**
 * User: vplociennik
 * Date: 11/27/13
 * Time: 2:17 AM
 */
public interface VmDao {

    public List<VmCPU> getCpu(String vmHostName , int rollupStamp);

    public List<VmMemory>   getVmMemoryData(String vmHostName , int rollupStamp);

    public List<VmDisk>   getVmDiskData(String vmHostName , int rollupStamp);

    public List<VmNetwork>   getVmNetworkData(String vmHostName , int rollupStamp);

    public List<VmProcess>  getVmProcessData(String vmHostName , int rollupStamp);

    public List<HostInfo>  getHostInfo(String hostName , int rollupStamp);
    
}
