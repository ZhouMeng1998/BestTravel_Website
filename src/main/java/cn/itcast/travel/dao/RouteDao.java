package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Route;

import java.util.List;

public interface RouteDao {
    public int queryTotalCount(int cid, String rname);
    public List<Route> queryPage(int cid, int start, int rows, String rname);
    public Route findOne(String rid);
}
