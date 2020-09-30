package cn.itcast.travel.web.servlet;

import cn.itcast.travel.service.UserService;
import cn.itcast.travel.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/activeUserServlet")
public class ActiveUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");
        response.setContentType("text/html;charset=utf-8");
        if(code != null) {
            UserService service = new UserServiceImpl();
            boolean flag = service.active(code);
            if(flag) {
                response.getWriter().write("User activation succeed, please<a href='http://localhost/travel/login.html'>Log in</a>");
            }else {
                response.getWriter().write("User activation failed, please contact us on BestTravel support page");
            }
        }
    }
}
