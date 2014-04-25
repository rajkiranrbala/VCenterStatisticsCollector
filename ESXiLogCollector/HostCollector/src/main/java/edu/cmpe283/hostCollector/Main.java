package edu.cmpe283.hostCollector;

import com.vmware.vim25.PerfCounterInfo;
import com.vmware.vim25.mo.PerformanceManager;
import com.vmware.vim25.mo.ServiceInstance;
import edu.cmpe283.hostCollector.config.Config;
import edu.cmpe283.hostCollector.manager.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * User: vplociennik
 * Date: 11/21/13
 * Time: 2:13 AM
 */
public class Main {

    private static ServiceInstance si;
    private static ResourceManager resourceManager;

    public static void main(String[] args) throws IOException {
        System.out.println("Hello world!");

        if(!connection(args))
            return;

        resourceManager = new ResourceManager(si);
        new LogHostManager(resourceManager.getHostList(), resourceManager.getPerfCounterInfoHostList(), resourceManager.getPerformanceManager()).startCollector();
        new LogDatastoreManager(resourceManager.getDatastoreList(), resourceManager.getPerfCounterInfoDatastoreList(), resourceManager.getPerformanceManager()).startCollector();
        new LogTaskManager(si.getTaskManager()).startCollector();

    }

    private static boolean connection(String[] args){

        if(args.length >= 1){
            Config.load(args[0]);
            si = new LoginManager().connect();
        } else {
            System.out.println("Need arguments : config_file");
            return false;
        }

        if(si == null){
            System.out.println("Error with credentials : can't connect");
            System.out.println("Need arguments : config_file");
            return false;
        }

        return true;
    }
}
