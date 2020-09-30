package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.RouteDao;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class RouteDaoImpl implements RouteDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public int queryTotalCount(int cid, String rname) {
//        String sql = "select count(*) from tab_route where cid = ?";
        String sql = "select count(*) from tab_route where 1 = 1 ";
        StringBuilder sb = new StringBuilder(sql);
        List<Object> params = new ArrayList<Object>();
        if(cid != 0){
            sb.append(" and cid = ? ");
            params.add(cid);
        }
        if(rname != null && rname.length() > 0){
            sb.append(" and rname like ? ");
            params.add("%"+rname+"%");
        }
        sql = sb.toString();
        int totalCount = template.queryForObject(sql, Integer.class, params.toArray());
        return totalCount;
    }

    @Override
    public List<Route> queryPage(int cid, int start, int rows, String rname) {
//        String sql = "select * from tab_route where cid = ? limit ?, ?";
        String sql = "select * from tab_route where 1 = 1 ";
        StringBuilder sb = new StringBuilder(sql);
        List<Object> params = new ArrayList<Object>();
        if(cid != 0){
            sb.append(" and cid = ? ");
            params.add(cid);
        }
        if(rname != null && rname.length() > 0){
            sb.append(" and rname like ? ");
            params.add("%"+rname+"%");
        }
        sb.append(" limit ?, ? ");
        params.add(start);
        params.add(rows);
        sql = sb.toString();
        List<Route> routes = template.query(sql, new BeanPropertyRowMapper<Route>(Route.class), params.toArray());
        return routes;
    }

    @Override
    public Route findOne(String rid) {
        String sql = "select * from tab_route where rid = ? ";
        return template.queryForObject(sql, new BeanPropertyRowMapper<Route>(Route.class), Integer.parseInt(rid));
    }
}
