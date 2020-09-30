package cn.itcast.travel.service;

import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;

public interface RouteService {
    public PageBean pageQuery(int curPage, int cid, int pageRows, String rname);

    Route findOne(String rid);
}
