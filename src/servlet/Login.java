package servlet;

import pojo.User;
import service.UserService;
import service.impl.UserServiceImpl;

import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//登录控制层
@WebServlet(name = "Login",urlPatterns = "/login.html")
public class Login extends HttpServlet {
    UserService userService=new UserServiceImpl();

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String flag=req.getParameter("flag");
        System.out.print(flag);
        if("login".equals(flag)){//登录
            login(req,resp);
        }
    }
    //登录
    public void login(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String userCode=req.getParameter("userCode");//相当于获取用户名
        String userPassword=req.getParameter("userPassword");//密码
        User u=new User();//new一个user对象
        u.setUserCode(userCode);//给用户名赋值
        u.setUserPassword(userPassword);//给密码赋值
        //调用方法
        User user= userService.login(u);
        if(user!=null){
            req.getSession().setAttribute("userSession",user);
            resp.sendRedirect("/jsp/frame.jsp");
        }else{
            req.setAttribute("error","用户名或者密码错误！");
            req.getRequestDispatcher("login.jsp").forward(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       doGet(req,resp);
    }
}
