package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.ResultInfo;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;
import cn.itcast.travel.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/user/*")
public class UserServlet extends BaseServlet {
    private UserService service = new UserServiceImpl();
    public void active(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String code = request.getParameter("code");
        response.setContentType("text/html;charset=utf-8");
        if(code != null) {
//            UserService service = new UserServiceImpl();
            boolean flag = service.active(code);
            if(flag) {
                response.getWriter().write("User activation succeed, please<a href='http://localhost/travel/login.html'>Log in</a>");
            }else {
                response.getWriter().write("User activation failed, please contact us on BestTravel support page");
            }
        }
    }
    public void exit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().invalidate();
        response.sendRedirect(request.getContextPath()+"/login.html");
    }
    public void find(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = (User)request.getSession().getAttribute("user");
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(), user);
        System.out.println(user.getUsername());
    }
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, String[]> map = request.getParameterMap();
        User user = new User();
        try {
            BeanUtils.populate(user, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
//        UserService service = new UserServiceImpl();
        User _user = service.login(user);
        ResultInfo info = new ResultInfo();
        if(_user == null){
            info.setErrorMsg("wrong username or password");
            info.setFlag(false);
        }
        if(_user != null && "N".equals(_user.getStatus())){
            info.setErrorMsg("Your account has not been activated, please check your email.");
            info.setFlag(false);
        }
        if(_user != null && "Y".equals(_user.getStatus())){
            info.setFlag(true);
        }
        request.getSession().setAttribute("user", _user);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(info);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }
    public void register(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String user_checkCode = request.getParameter("check");
        System.out.println("user_checkCode: " + user_checkCode);
        String server_checkCode = (String) request.getSession().getAttribute("CHECKCODE_SERVER");
        System.out.println("server_checkCode: " + server_checkCode);
        request.getSession().removeAttribute("CHECKCODE_SERVER");
        if(server_checkCode == null || !user_checkCode.equalsIgnoreCase(server_checkCode)){
            ResultInfo info = new ResultInfo();
            info.setFlag(false);
            info.setErrorMsg("验证码错误");
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(info);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(json);
            return;
        }

        Map<String, String[]> map = request.getParameterMap();
        User user = new User();
        try {
            BeanUtils.populate(user, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        boolean flag = service.register(user);
        ResultInfo info = new ResultInfo();
        if(flag) {
            info.setFlag(true);
        }else{
            info.setFlag(false);
            info.setErrorMsg("注册失败");
        }
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(info);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }
}
