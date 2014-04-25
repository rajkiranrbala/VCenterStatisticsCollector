package edu.cmpe283.prj2.dao;

import edu.cmpe283.prj2.model.stats.HostInfo;
import edu.cmpe283.prj2.model.stats.VmCPU;
import edu.cmpe283.prj2.model.stats.VmDisk;
import edu.cmpe283.prj2.model.stats.VmMemory;
import edu.cmpe283.prj2.model.stats.VmNetwork;
import edu.cmpe283.prj2.model.stats.VmProcess;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.util.List;


public class JdbcVmDao extends JdbcDaoSupport implements VmDao {


    @Override
    public List<VmCPU> getCpu(String vmHostName , int rollupStamp) {

        String sql = null ;
        if (vmHostName.equals("allVm"))
        {
            sql = "SELECT * FROM vmcpuinfo  where rollupStamp = " + rollupStamp + " ORDER BY DateTime ASC "   ;
        }
        else
        {
            sql ="SELECT * FROM vmcpuinfo where hostName= '" + vmHostName + "'  and  rollupStamp ="  + rollupStamp  + " ORDER BY DateTime ASC ";
        }

        List<VmCPU> vmCPUs = getJdbcTemplate().query(sql,
                new BeanPropertyRowMapper(VmCPU.class));

        return vmCPUs;
    }

    @Override
    public List<VmNetwork> getVmNetworkData(String vmHostName , int  rollupStamp) {



        String sqlQuery = null ;
        if (vmHostName.equals("allVm"))
        {
            sqlQuery = "SELECT * FROM vmnetinfo  where rollupStamp = " + rollupStamp + " ORDER BY DateTime ASC "   ;
        }
        else
        {
            sqlQuery ="SELECT * FROM vmnetinfo where hostName= '" + vmHostName + "'  and  rollupStamp ="  + rollupStamp  + " ORDER BY DateTime ASC ";
        }

        System.out.println("the sql quey is " + sqlQuery);

        List<VmNetwork> vmNetworkList = getJdbcTemplate().query(sqlQuery,
                new BeanPropertyRowMapper(VmNetwork.class));
        return vmNetworkList;


    }

    @Override
    public List<VmMemory> getVmMemoryData(String vmHostName , int  rollupStamp) {



        String sqlQuery = null ;
        if (vmHostName.equals("allVm"))
        {
            sqlQuery = "SELECT * FROM vmmeminfo  where rollupStamp = " + rollupStamp + " ORDER BY DateTime ASC "   ;
        }
        else
        {
            sqlQuery ="SELECT * FROM vmmeminfo where hostName= '" + vmHostName + "'  and  rollupStamp ="  + rollupStamp  + " ORDER BY DateTime ASC ";
        }



        List<VmMemory> vmMemoryList = getJdbcTemplate().query(sqlQuery,
                new BeanPropertyRowMapper(VmMemory.class));
        return vmMemoryList;


    }



    @Override
    public List<VmProcess> getVmProcessData(String vmHostName , int  rollupStamp) {



        String sqlQuery = null ;
        if (vmHostName.equals("allVm"))
        {
            sqlQuery = "SELECT * FROM vmprocessinfo  where rollupStamp = " + rollupStamp + " ORDER BY DateTime ASC "   ;
        }
        else
        {
            sqlQuery ="SELECT * FROM vmprocessinfo where hostName= '" + vmHostName + "'  and  rollupStamp ="  + rollupStamp  + " ORDER BY DateTime ASC ";
        }

        List<VmProcess> vmProcessList = getJdbcTemplate().query(sqlQuery,
                new BeanPropertyRowMapper(VmProcess.class));


        return vmProcessList;
    }

    @Override
    public List<VmDisk> getVmDiskData(String vmHostName , int rollupStamp) {

        String sqlQuery = null ;
        if (vmHostName.equals("allVm"))
        {
            sqlQuery = "SELECT * FROM vmdiskinfo  where rollupStamp = " + rollupStamp + " ORDER BY DateTime ASC "   ;
        }
        else
        {
            sqlQuery ="SELECT * FROM vmdiskinfo where hostName= '" + vmHostName + "'  and  rollupStamp ="  + rollupStamp  + " ORDER BY DateTime ASC ";
        }


        List<VmDisk> vmDiskList = getJdbcTemplate().query(sqlQuery,
                new BeanPropertyRowMapper(VmDisk.class));
        return vmDiskList;


    }

    @Override
    public List<HostInfo> getHostInfo(String hostName , int rollupStamp) {



        String sqlQuery = null ;
        if (hostName.equals("allHost"))
        {
            sqlQuery = "SELECT * FROM hostinfo  where rollupStamp = " + rollupStamp + " ORDER BY DateTime ASC "   ;
        }
        else
        {
            sqlQuery ="SELECT * FROM hostinfo where hostName= '" + hostName + "'  and  rollupStamp ="  + rollupStamp  + " ORDER BY DateTime ASC ";
        }

        List<HostInfo> hostCpuList = getJdbcTemplate().query(sqlQuery,
                new BeanPropertyRowMapper(HostInfo.class));
        return hostCpuList;


    }

}
