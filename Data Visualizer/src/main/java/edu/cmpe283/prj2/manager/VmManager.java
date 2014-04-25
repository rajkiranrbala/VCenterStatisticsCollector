package edu.cmpe283.prj2.manager;

import edu.cmpe283.prj2.dao.JdbcVmDao;
import edu.cmpe283.prj2.model.stats.*;
import edu.cmpe283.prj2.model.util.ChartData;

import edu.cmpe283.prj2.model.chart.ChartStructure;
import edu.cmpe283.prj2.model.chart.Series;
import edu.cmpe283.prj2.model.chart.XAxis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.*;


public class VmManager {


	@Qualifier("vmDao")
	@Autowired
	private JdbcVmDao vmDao;

    public ChartStructure getVmCpu(String vmHostName, int rollupStamp) {

        ChartStructure chartStructure = new ChartStructure("Cpu", "chart", "Cpu usage (%)", rollupStamp);

        List<VmCPU> l = vmDao.getCpu( vmHostName ,  rollupStamp);


        List<ChartData> ChartDataList = new ArrayList<ChartData>();
        for (int i =0 ; i <l.size() ; i++ )
        {
            System.out.println(l.get(i).getHostName());
            ChartData tempChartData = new ChartData();
            tempChartData.setDateTime(l.get(i).getDateTime());
            tempChartData.setHostName(l.get(i).getHostName());
            tempChartData.setValue(l.get(i).getcPUUsage());
            ChartDataList.add(tempChartData);
        }

        return convertToChartFormat2(chartStructure, ChartDataList);

    }

    public ChartStructure getVmMemory(String vmHostName, int rollupStamp) {

        ChartStructure chartStructure = new ChartStructure("Memory", "Memory chart", "Memory Used (%)", rollupStamp);

        List<VmMemory> l = vmDao.getVmMemoryData(vmHostName ,  rollupStamp);

        List<ChartData> ChartDataList = new ArrayList<ChartData>();
        for (int i =0 ; i <l.size() ; i++ )
        {
            System.out.println(l.get(i).getHostName());
            ChartData tempChartData = new ChartData();
            tempChartData.setDateTime(l.get(i).getDateTime());
            tempChartData.setHostName(l.get(i).getHostName());
            tempChartData.setValue(100*l.get(i).getTotalMem()/l.get(i).getUsedMem());
            ChartDataList.add(tempChartData);
        }

        return convertToChartFormat2(chartStructure, ChartDataList);

    }

    public ChartStructure getVmNetworkTx(String vmHostName, int rollupStamp)
    {

        ChartStructure chartStructure = new ChartStructure("Network", "Data sent", "Data sent (KBps)", rollupStamp);

        List<VmNetwork> inputNetworkTxList = vmDao.getVmNetworkData(vmHostName ,  rollupStamp);

        List<ChartData> ChartDataList = new ArrayList<ChartData>();
        for (int i =0 ; i <inputNetworkTxList.size() ; i++ )
    {
        System.out.println(inputNetworkTxList.get(i).getHostName());
        ChartData tempChartData = new ChartData();
        tempChartData.setDateTime(inputNetworkTxList.get(i).getDateTime());
        tempChartData.setHostName(inputNetworkTxList.get(i).getHostName());
        tempChartData.setValue(inputNetworkTxList.get(i).getTx());
        ChartDataList.add(tempChartData);
    }

    return convertToChartFormat2(chartStructure, ChartDataList);



}

    public ChartStructure getVmNetworkRx(String vmHostName, int rollupStamp)
    {

        ChartStructure chartStructure = new ChartStructure("Network", "Data received", "Data received (KBps)", rollupStamp);

        List<VmNetwork> inputNetworkTxList = vmDao.getVmNetworkData(vmHostName ,  rollupStamp);

        List<ChartData> ChartDataList = new ArrayList<ChartData>();
        for (int i =0 ; i <inputNetworkTxList.size() ; i++ )
        {
            System.out.println(inputNetworkTxList.get(i).getHostName());
            ChartData tempChartData = new ChartData();
            tempChartData.setDateTime(inputNetworkTxList.get(i).getDateTime());
            tempChartData.setHostName(inputNetworkTxList.get(i).getHostName());
            tempChartData.setValue(inputNetworkTxList.get(i).getRx());
            ChartDataList.add(tempChartData);
        }

        return convertToChartFormat2(chartStructure, ChartDataList);

    }

    public ChartStructure getVmProcessThreads(String vmHostName, int rollupStamp)
    {
        ChartStructure chartStructure = new ChartStructure("Threads", "chart", "Number of threads", rollupStamp);

        List<VmProcess> inputProcessList = vmDao.getVmProcessData(vmHostName ,  rollupStamp);

        //convert into chartAxis Pojo

        List<ChartData> ChartDataList = new ArrayList<ChartData>();
        for (int i =0 ; i <inputProcessList.size() ; i++ )
        {
            ChartData tempChartData = new ChartData();
            tempChartData.setDateTime(inputProcessList.get(i).getDateTime());
            tempChartData.setHostName(inputProcessList.get(i).getHostName());
            tempChartData.setValue(inputProcessList.get(i).getThreads());
            ChartDataList.add(tempChartData);
        }

        return convertToChartFormat2(chartStructure, ChartDataList);


    }

    public ChartStructure getVmDiskRead(String vmHostName, int rollupStamp)
    {
        ChartStructure chartStructure = new ChartStructure("Disk IO", "reads", "Number of reads", rollupStamp);

        List<VmDisk> l = vmDao.getVmDiskData(vmHostName, rollupStamp);
        System.out.println(l.size());

        List<ChartData> ChartDataList = new ArrayList<ChartData>();
        for (int i =0 ; i <l.size() ; i++ )
        {
            ChartData tempChartData = new ChartData();
            tempChartData.setDateTime(l.get(i).getDateTime());
            tempChartData.setHostName(l.get(i).getHostName());
            tempChartData.setValue(l.get(i).getReadIO());
            ChartDataList.add(tempChartData);
        }

        return convertToChartFormat2(chartStructure, ChartDataList);


    }

    public ChartStructure getVmDiskWrite(String vmHostName, int rollupStamp)
    {
        ChartStructure chartStructure = new ChartStructure("Disk IO", "write", "Number of writes", rollupStamp);

        List<VmDisk> l = vmDao.getVmDiskData(vmHostName, rollupStamp);

        List<ChartData> ChartDataList = new ArrayList<ChartData>();
        for (int i =0 ; i <l.size() ; i++ )
        {
            ChartData tempChartData = new ChartData();
            tempChartData.setDateTime(l.get(i).getDateTime());
            tempChartData.setHostName(l.get(i).getHostName());
            tempChartData.setValue(l.get(i).getWriteIO());
            ChartDataList.add(tempChartData);
        }

        return convertToChartFormat2(chartStructure, ChartDataList);


    }




		public ChartStructure getHostCPU(String vmHostName, int rollupStamp) {


            ChartStructure chartStructure = new ChartStructure("Cpu", "chart", "Cpu usage (%)", rollupStamp);

            List<HostInfo> l = vmDao.getHostInfo(vmHostName, rollupStamp);

            List<ChartData> ChartDataList = new ArrayList<ChartData>();
            for (int i =0 ; i <l.size() ; i++ )
            {
                ChartData tempChartData = new ChartData();
                tempChartData.setDateTime(l.get(i).getDateTime());
                tempChartData.setHostName(l.get(i).getHostname());
                tempChartData.setValue(l.get(i).getCpuPercentage());
                ChartDataList.add(tempChartData);
            }

            return convertToChartFormat2(chartStructure, ChartDataList);

		}



    public ChartStructure getHostMemory(String vmHostName, int rollupStamp) {

        ChartStructure chartStructure = new ChartStructure("Memory", "Memory chart", "Memory Used (%)", rollupStamp);

        List<HostInfo> l = vmDao.getHostInfo(vmHostName ,  rollupStamp);

        List<ChartData> ChartDataList = new ArrayList<ChartData>();
        for (int i =0 ; i <l.size() ; i++ )
        {
            System.out.println(l.get(i).getHostname());
            ChartData tempChartData = new ChartData();
            tempChartData.setDateTime(l.get(i).getDateTime());
            tempChartData.setHostName(l.get(i).getHostname());
            tempChartData.setValue(l.get(i).getMemPercentage());
            ChartDataList.add(tempChartData);
        }

        return convertToChartFormat2(chartStructure, ChartDataList);

    }

    public ChartStructure getHostNetworkTx(String vmHostName, int rollupStamp)
    {

        ChartStructure chartStructure = new ChartStructure("Network", "Data sent", "Data sent (KBps)", rollupStamp);

        List<HostInfo> l = vmDao.getHostInfo(vmHostName ,  rollupStamp);

        List<ChartData> ChartDataList = new ArrayList<ChartData>();
        for (int i =0 ; i <l.size() ; i++ )
        {
            System.out.println(l.get(i).getHostname());
            ChartData tempChartData = new ChartData();
            tempChartData.setDateTime(l.get(i).getDateTime());
            tempChartData.setHostName(l.get(i).getHostname());
            tempChartData.setValue(l.get(i).getTx());
            ChartDataList.add(tempChartData);
        }

        return convertToChartFormat2(chartStructure, ChartDataList);



    }

    public ChartStructure getHostNetworkRx(String vmHostName, int rollupStamp)
    {

        ChartStructure chartStructure = new ChartStructure("Network", "Data received", "Data received (KBps)", rollupStamp);

        List<HostInfo> l = vmDao.getHostInfo(vmHostName ,  rollupStamp);

        List<ChartData> ChartDataList = new ArrayList<ChartData>();
        for (int i =0 ; i <l.size() ; i++ )
        {
            System.out.println(l.get(i).getHostname());
            ChartData tempChartData = new ChartData();
            tempChartData.setDateTime(l.get(i).getDateTime());
            tempChartData.setHostName(l.get(i).getHostname());
            tempChartData.setValue(l.get(i).getRx());
            ChartDataList.add(tempChartData);
        }

        return convertToChartFormat2(chartStructure, ChartDataList);

    }



    private ChartStructure convertToChartFormat2(ChartStructure chartStructure, List<ChartData> chartDataList) {
        Map<String, Map<Date ,Double > > hostValuemap = new HashMap<String, Map<Date ,Double >>();

        Set<Date> timestampHashSet = new HashSet<Date>();

        for (int i =0 ; i <chartDataList.size() ; i++ ){

            ChartData tempData = chartDataList.get(i);


            timestampHashSet.add(tempData.getDateTime());

            String tempVMHostName = tempData.getHostName();

            if(hostValuemap.containsKey(tempVMHostName)){
                hostValuemap.get(tempVMHostName).put(tempData.getDateTime(), tempData.getValue());
            } else {
                Map<Date ,Double > timeValueMap = new HashMap<Date ,Double >();
                timeValueMap.put(tempData.getDateTime(), tempData.getValue());
                hostValuemap.put(tempVMHostName, timeValueMap);
            }


        }
        List<Date> xAxis = new ArrayList<Date>(timestampHashSet);
        Collections.sort(xAxis);

        List<Date> xAxis2 = new ArrayList<Date>();
        for (int i = xAxis.size() >= 60 ? xAxis.size() - 60 : 0; i < xAxis.size(); i++) {
            xAxis2.add(xAxis.get(i));
        }


        chartStructure.setxAxis(new XAxis(xAxis2));

        List<Series> seriesList = new ArrayList<Series>();
        for (String hostName : hostValuemap.keySet()){
            seriesList.add(new Series(hostName, getData(hostValuemap.get(hostName), xAxis2)));
        }

        chartStructure.setSeries(seriesList);

        return chartStructure;
    }

    private List<Double> getData(Map<Date, Double> map, List<Date> xAxis) {

        List<Double> l = new ArrayList<Double>();


        for (int i = 0 ; i < xAxis.size() ; i++)
        {
            if(map.containsKey(xAxis.get(i)))
            {
                l.add(map.get(xAxis.get(i)));
            }
            else
            {
                l.add(null);
            }
        }

        return l;
    }




}
