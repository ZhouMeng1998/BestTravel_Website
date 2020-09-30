package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.FavoriteService;
import cn.itcast.travel.service.RouteService;
import cn.itcast.travel.service.impl.FavoriteServiceImpl;
import cn.itcast.travel.service.impl.RouteServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/route/*")
public class RouteServlet extends BaseServlet {
    private RouteService service = new RouteServiceImpl();
    private FavoriteService favoriteService = new FavoriteServiceImpl();
    public void findRoute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String curPageStr = request.getParameter("currentPage");
        String pageRowsStr = request.getParameter("pageRows");
        String cidStr = request.getParameter("cid");
        String rname = request.getParameter("rname");
        if (rname != null && rname.length() > 0 && !"null".equals(rname)) {
            rname = new String(rname.getBytes("iso-8859-1"), "utf-8");
        } else {
            rname = null;
        }
        int curPage = 1;
        if (curPageStr != null && curPageStr.length() > 0) {
            curPage = Integer.parseInt(curPageStr);
        }
        int pageRows = 5;
        if (pageRowsStr != null && pageRowsStr.length() > 0) {
            pageRows = Integer.parseInt(pageRowsStr);
        }
        int cid = 0;
        if (cidStr != null && cidStr.length() > 0) {
            cid = Integer.parseInt(cidStr);
        }
        PageBean pb = service.pageQuery(curPage, cid, pageRows, rname);
        writeValue(response, pb);
    }

    public void findOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String rid = request.getParameter("rid");
        Route route = service.findOne(rid);
        writeValue(response, route);
    }

    public void isFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String rid = request.getParameter("rid");
        User user = (User)request.getSession().getAttribute("user");
        boolean flag = false;
        if(user != null) {
            flag = favoriteService.isFavorite(rid, user.getUid());
        }
        writeValue(response, flag);
    }
    public void addFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String rid = request.getParameter("rid");
        User user = (User)request.getSession().getAttribute("user");
        if(user == null){
            return;
        }
        favoriteService.addFavorite(rid, user.getUid());
    }
}