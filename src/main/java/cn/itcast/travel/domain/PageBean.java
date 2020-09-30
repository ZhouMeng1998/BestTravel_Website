package cn.itcast.travel.domain;

import java.util.List;

public class PageBean {
    private int currentPage; //当前页码
    private int totalPage;  //总页数
    private int totalCount; //总记录数
    private int pageRows; //每页显示条数
    private List<Route> routes;

    @Override
    public String toString() {
        return "PageBean{" +
                "currentPage=" + currentPage +
                ", totalPage=" + totalPage +
                ", totalCount=" + totalCount +
                ", pageRows=" + pageRows +
                ", routes=" + routes +
                '}';
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getPageRows() {
        return pageRows;
    }

    public void setPageRows(int pageRows) {
        this.pageRows = pageRows;
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }
}
