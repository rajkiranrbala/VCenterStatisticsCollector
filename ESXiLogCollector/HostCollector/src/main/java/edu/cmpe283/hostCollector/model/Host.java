package edu.cmpe283.hostCollector.model;

import com.vmware.vim25.*;
import com.vmware.vim25.mo.HostSystem;
import com.vmware.vim25.mo.PerformanceManager;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * User: vplociennik
 * Date: 11/21/13
 * Time: 4:06 AM
 */
public class Host {

    private HostSystem h;

    public Host(HostSystem h) {
        this.h = h;
    }

    public HostStat getHostStat(PerformanceManager performanceManager, List<PerfCounterInfo> perfCounterInfoList) throws RemoteException {
        HostStat hs = new HostStat();

        if(h.getSummary().quickStats.getOverallCpuUsage() == null)
            return null;

        hs.setCpuusage(Long.valueOf(h.getSummary().quickStats.getOverallCpuUsage()));
        hs.setMemusage(Long.valueOf(h.getSummary().quickStats.getOverallMemoryUsage()));
        hs.setHostname(h.getName());
        hs.setUptime(Long.valueOf(h.getSummary().quickStats.getUptime()));
        hs.setCpumax(Long.valueOf(h.getSummary().hardware.getCpuMhz() * h.getSummary().hardware.getNumCpuCores()));
        hs.setMemmax(h.getSummary().hardware.getMemorySize() / 1024 / 1024);
        hs.setCpupercentage(hs.getCpuusage() * 100 / hs.getCpumax());
        hs.setMempercentage(hs.getMemusage() * 100 / hs.getMemmax());

        PerfQuerySpec perfQuerySpec = new PerfQuerySpec();
        perfQuerySpec.setEntity(h.getMOR());
        perfQuerySpec.setMaxSample(2);
        perfQuerySpec.setFormat("normal");
        perfQuerySpec.setIntervalId(performanceManager.queryPerfProviderSummary(h).getRefreshRate());
        PerfEntityMetricBase[] pValues = performanceManager
                .queryPerf(new PerfQuerySpec[] { perfQuerySpec });

        if (pValues != null) {
            hs.setNet(generatePerformanceResult(pValues, perfCounterInfoList));
        }


        return hs;
    }

    public List<String> generatePerformanceResult(PerfEntityMetricBase[] pValues, List<PerfCounterInfo> perfCounterInfoList) {
        List<String> out = new ArrayList<String>();
        //System.out.println("in");
        Map<Integer, PerfCounterInfo> perfCounterInfoMap = new HashMap<Integer, PerfCounterInfo>();

        for (PerfCounterInfo perfCounterInfo : perfCounterInfoList) {
            perfCounterInfoMap.put(perfCounterInfo.getKey(), perfCounterInfo);
        }

        for (PerfEntityMetricBase p : pValues) {
            PerfEntityMetric pem = (PerfEntityMetric) p;
            PerfMetricSeries[] pms = pem.getValue();
            for (PerfMetricSeries pm : pms) {
                int counterId = pm.getId().getCounterId();
                PerfCounterInfo info = perfCounterInfoMap.get(new Integer(counterId));

                if(info == null)
                    continue;

                String value = "";

                if (pm instanceof PerfMetricIntSeries) {
                    PerfMetricIntSeries series = (PerfMetricIntSeries) pm;
                    long[] values = series.getValue();
                    long result = 0;
                    for (long v : values) {
                        result += v;
                    }
                    result = (long) (result / values.length);
                    value = String.valueOf(result);
                    //System.out.println(value);
                } else if (pm instanceof PerfMetricSeriesCSV) {
                    PerfMetricSeriesCSV seriesCsv = (PerfMetricSeriesCSV) pm;
                    value = seriesCsv.getValue() + " in "
                            + info.getUnitInfo().getLabel();
                }

                out.add(value);
            }


        }

        return out;
    }


    public HostSystem getH() {
        return h;
    }

    public void setH(HostSystem h) {
        this.h = h;
    }
}
