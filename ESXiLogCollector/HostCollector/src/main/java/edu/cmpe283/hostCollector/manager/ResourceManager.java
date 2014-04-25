package edu.cmpe283.hostCollector.manager;


import com.vmware.vim25.PerfCounterInfo;
import com.vmware.vim25.mo.*;
import edu.cmpe283.hostCollector.config.Config;
import edu.cmpe283.hostCollector.model.Host;
import java.rmi.RemoteException;
import java.util.*;

/**
 * User: vplociennik
 * Date: 11/21/13
 * Time: 2:46 AM
 */
public class ResourceManager {

    private ServiceInstance si;
    private List<Host> hostList;
    private List<Datastore> datastoreList;
    private ArrayList<PerfCounterInfo> perfCounterInfoHostList;
    private ArrayList<PerfCounterInfo> perfCounterInfoDatastoreList;
    private PerformanceManager performanceManager;

    public ResourceManager(ServiceInstance si) throws RemoteException {
        this.si = si;
        hostList = new ArrayList<Host>();
        datastoreList = new ArrayList<Datastore>();
        performanceManager = si.getPerformanceManager();

        counterInfo();
        crawl();
    }

    private void counterInfo() {

        PerformanceManager performanceManager = si.getPerformanceManager();
        perfCounterInfoHostList = new ArrayList<PerfCounterInfo>();
        perfCounterInfoDatastoreList= new ArrayList<PerfCounterInfo>();

        PerfCounterInfo[] infos = performanceManager.getPerfCounter();
        for (PerfCounterInfo info : infos) {
            if(info.getNameInfo().getKey().equals("received") || info.getNameInfo().getKey().equals("transmitted")){
                perfCounterInfoHostList.add(info);
                System.out.println(String.format("[%s.%s]", info.getNameInfo().getKey(), info.getRollupType()));
            }
            if(info.getNameInfo().getKey().equals("datastoreWriteOIO") || info.getNameInfo().getKey().equals("datastoreReadOIO")){
                perfCounterInfoDatastoreList.add(info);
                System.out.println(String.format("[%s.%s]", info.getNameInfo().getKey(), info.getRollupType()));
            }
        }
    }

    private void crawl() throws RemoteException {
        ManagedEntity[] managedEntities = new InventoryNavigator(si.getRootFolder()).searchManagedEntities(new String[][] { {"HostSystem", "name" }, }, true);

        for (ManagedEntity managedEntity : managedEntities) {
            if(Config.HOSTS.contains(((HostSystem) managedEntity).getName())){
                hostList.add(new Host((HostSystem) managedEntity));
            }

        }

        Set<Datastore> s = new HashSet<Datastore>();
        for (Host host : hostList) {
            for (Datastore datastore : host.getH().getDatastores()) {
                s.add(datastore);
            }
        }
        datastoreList.addAll(s);
    }

    public List<Datastore> getDatastoreList() {
        return datastoreList;
    }

    public void setDatastoreList(List<Datastore> datastoreList) {
        this.datastoreList = datastoreList;
    }

    public void setHostList(List<Host> hostList) {
        this.hostList = hostList;
    }

    public List<Host> getHostList() {
        return hostList;
    }

    public PerformanceManager getPerformanceManager() {
        return performanceManager;
    }

    public void setPerformanceManager(PerformanceManager performanceManager) {
        this.performanceManager = performanceManager;
    }

    public ArrayList<PerfCounterInfo> getPerfCounterInfoHostList() {
        return perfCounterInfoHostList;
    }

    public void setPerfCounterInfoHostList(ArrayList<PerfCounterInfo> perfCounterInfoHostList) {
        this.perfCounterInfoHostList = perfCounterInfoHostList;
    }

    public ArrayList<PerfCounterInfo> getPerfCounterInfoDatastoreList() {
        return perfCounterInfoDatastoreList;
    }

    public void setPerfCounterInfoDatastoreList(ArrayList<PerfCounterInfo> perfCounterInfoDatastoreList) {
        this.perfCounterInfoDatastoreList = perfCounterInfoDatastoreList;
    }
}
