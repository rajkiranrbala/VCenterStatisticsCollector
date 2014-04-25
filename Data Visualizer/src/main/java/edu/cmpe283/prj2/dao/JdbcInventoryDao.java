package edu.cmpe283.prj2.dao;

import edu.cmpe283.prj2.model.util.Str;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * User: vplociennik
 * Date: 12/1/13
 * Time: 3:32 AM
 */
public class JdbcInventoryDao extends JdbcDaoSupport implements InventoryDao {

    @Override
    public List<String> getHostList() {

        String sqlQuery = "SELECT DISTINCT hostname AS str FROM hostinfo;";

        List<Str> res = getJdbcTemplate().query(sqlQuery,
                new BeanPropertyRowMapper(Str.class));

        List<String> res2 = new ArrayList<String>();
        for (Str re : res) {
            res2.add(re.getStr());
        }

        return res2;

    }

    @Override
    public List<String> getVmList() {
        String sqlQuery = "SELECT DISTINCT HostName AS str FROM vmcpuinfo;";

        List<Str> res = getJdbcTemplate().query(sqlQuery,
                new BeanPropertyRowMapper(Str.class));

        List<String> res2 = new ArrayList<String>();
        for (Str re : res) {
            res2.add(re.getStr());
        }

        return res2;
    }
}
