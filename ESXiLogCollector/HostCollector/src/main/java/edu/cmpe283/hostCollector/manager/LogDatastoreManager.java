package edu.cmpe283.hostCollector.manager;

import com.vmware.vim25.*;
import com.vmware.vim25.mo.Datastore;
import com.vmware.vim25.mo.PerformanceManager;
import edu.cmpe283.hostCollector.config.Config;
import edu.cmpe283.hostCollector.model.DatastoreStat;
import edu.cmpe283.hostCollector.util.GenericCollector;
import edu.cmpe283.hostCollector.util.LogWriter;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: vplociennik
 * Date: 11/22/13
 * Time: 2:43 AM
 */
public class LogDatastoreManager extends GenericCollector<List<DatastoreStat>> {

    private List<Datastore> datastoreList;
    private LogWriter logWriter;
    private List<PerfCounterInfo> perfCounterInfoDatastoreList;
    private PerformanceManager performanceManager;

    public LogDatastoreManager(List<Datastore> datastoreList, ArrayList<PerfCounterInfo> perfCounterInfoDatastoreList, PerformanceManager performanceManager) throws IOException {
        super(new LogWriter(Config.ROOT+"datastore.log"), Config.SAMPLE_TIME);
        this.datastoreList = datastoreList;
        this.perfCounterInfoDatastoreList = perfCounterInfoDatastoreList;
        this.performanceManager = performanceManager;
    }

    @Override
    public List<DatastoreStat> getData() {
        List<DatastoreStat> l = new ArrayList<DatastoreStat>();

        for (Datastore datastore : datastoreList) {

            DatastoreStat ds = new DatastoreStat(datastore);

            PerfQuerySpec perfQuerySpec = new PerfQuerySpec();
            perfQuerySpec.setEntity(datastore.getMOR());
            //perfQuerySpec.setMaxSample(2);
            perfQuerySpec.setFormat("normal");

            try {

                PerfEntityMetricBase[] pValues = performanceManager.queryPerf(new PerfQuerySpec[]{perfQuerySpec});

                //System.out.println(pValues);
                if (pValues != null) {
                    ds.setIO(generatePerformanceResult(pValues, perfCounterInfoDatastoreList));
                }

                //System.out.println(ds);

                l.add(new DatastoreStat(datastore));

            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        return l;
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
}