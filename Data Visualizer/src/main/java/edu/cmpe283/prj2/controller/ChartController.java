package edu.cmpe283.prj2.controller;

import edu.cmpe283.prj2.manager.VmManager;
import edu.cmpe283.prj2.model.chart.ChartStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class ChartController {


    @Qualifier("vmManager")
    @Autowired
    private VmManager vmManager;



    @RequestMapping(value = "/vm/cpu/{name}/{val}")
    public @ResponseBody
    ChartStructure getVmCpuChart(@PathVariable String name, @PathVariable Integer val) {
        return vmManager.getVmCpu(name, val);
    }


    @RequestMapping(value = "/vm/memory/{name}/{val}")
    public @ResponseBody
    ChartStructure getMemoryChart(@PathVariable String name, @PathVariable Integer val) {
        return vmManager.getVmMemory(name, val);
    }

    @RequestMapping(value = "/vm/tx/{name}/{val}")
    public @ResponseBody
    ChartStructure getTxChart(@PathVariable String name, @PathVariable Integer val) {
        return vmManager.getVmNetworkTx(name, val);
    }

    @RequestMapping(value = "/vm/rx/{name}/{val}")
    public @ResponseBody
    ChartStructure getRxChart(@PathVariable String name, @PathVariable Integer val) {
        return vmManager.getVmNetworkRx(name, val);
    }

    @RequestMapping(value = "/vm/thread/{name}/{val}")
    public @ResponseBody
    ChartStructure getThreadChart(@PathVariable String name, @PathVariable Integer val) {
        return vmManager.getVmProcessThreads(name, val);
    }

    @RequestMapping(value = "/vm/ioread/{name}/{val}")
    public @ResponseBody
    ChartStructure getIORead(@PathVariable String name, @PathVariable Integer val) {
        return vmManager.getVmDiskRead(name, val);
    }

    @RequestMapping(value = "/vm/iowrite/{name}/{val}")
    public @ResponseBody
    ChartStructure getIOWrite(@PathVariable String name, @PathVariable Integer val) {
        return vmManager.getVmDiskWrite(name, val);
    }


    @RequestMapping(value = "/host/cpu/{name}/{val}")
    public @ResponseBody
    ChartStructure getHostCpuChart(@PathVariable String name, @PathVariable Integer val) {
        return vmManager.getHostCPU(name, val);
    }


    @RequestMapping(value = "/host/memory/{name}/{val}")
    public @ResponseBody
    ChartStructure getHostMemoryChart(@PathVariable String name, @PathVariable Integer val) {
        return vmManager.getHostMemory(name, val);
    }

    @RequestMapping(value = "/host/tx/{name}/{val}")
    public @ResponseBody
    ChartStructure getHostTxChart(@PathVariable String name, @PathVariable Integer val) {
        return vmManager.getHostNetworkTx(name, val);
    }

    @RequestMapping(value = "/host/rx/{name}/{val}")
    public @ResponseBody
    ChartStructure getHostRxChart(@PathVariable String name, @PathVariable Integer val) {
        return vmManager.getHostNetworkRx(name, val);
    }



}
