package edu.cmpe283.prj2.manager;

import edu.cmpe283.prj2.dao.JdbcInventoryDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * User: vplociennik
 * Date: 12/1/13
 * Time: 3:36 AM
 */
public class InventoryManager {

    @Autowired
    private JdbcInventoryDao inventoryDao;

    public List<String> getHostList(){
        return inventoryDao.getHostList();
    }


    public List<String> getVmList(){
        return inventoryDao.getVmList();
    }
}
