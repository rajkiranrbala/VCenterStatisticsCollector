package edu.cmpe283.hostCollector.manager;

import com.vmware.vim25.PerfCounterInfo;
import com.vmware.vim25.mo.PerformanceManager;
import edu.cmpe283.hostCollector.config.Config;
import edu.cmpe283.hostCollector.model.Host;
import edu.cmpe283.hostCollector.model.HostStat;
import edu.cmpe283.hostCollector.util.GenericCollector;
import edu.cmpe283.hostCollector.util.LogWriter;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

/**
 * User: vplociennik
 * Date: 11/21/13
 * Time: 4:05 AM
 */
public class LogHostManager extends GenericCollector<List<HostStat>> {

    private List<Host> hostList;
    private List<PerfCounterInfo> perfCounterInfoList;
    private LogWriter logWriter;
    private PerformanceManager performanceManager;

    public LogHostManager(List<Host> hostList, ArrayList<PerfCounterInfo> perfCounterInfoList, PerformanceManager performanceManager) throws IOException {
        super(new LogWriter(Config.ROOT+"host.log"), Config.SAMPLE_TIME);
        this.hostList = hostList;
        this.perfCounterInfoList = perfCounterInfoList;
        this.performanceManager = performanceManager;
    }

    @Override
    public List<HostStat> getData() {
        List<HostStat> l = new ArrayList<HostStat>();
        for (Host host : hostList) {
            try {
                l.add(host.getHostStat(performanceManager, perfCounterInfoList));
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        return l;
    }
}
