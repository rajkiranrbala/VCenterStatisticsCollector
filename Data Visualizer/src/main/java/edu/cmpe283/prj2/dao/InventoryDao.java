package edu.cmpe283.prj2.dao;

import java.util.List;

/**
 * User: vplociennik
 * Date: 12/1/13
 * Time: 3:32 AM
 */
public interface InventoryDao {

    public List<String> getHostList();
    public List<String> getVmList();

}
