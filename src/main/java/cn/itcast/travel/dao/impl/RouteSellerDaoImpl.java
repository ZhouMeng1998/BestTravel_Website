package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.RouteSellerDao;
import cn.itcast.travel.domain.RouteImg;
import cn.itcast.travel.domain.Seller;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class RouteSellerDaoImpl implements RouteSellerDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public Seller findSeller(int id) {
        String sql = "select * from tab_seller where sid = ? ";
        return template.queryForObject(sql, new BeanPropertyRowMapper<Seller>(Seller.class), id);
    }
}
