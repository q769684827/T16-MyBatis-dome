package servlet;

import com.alibaba.fastjson.JSONArray;
import pojo.Role;
import pojo.User;
import service.RoleService;
import service.UserService;
import service.impl.RoleServiceImpl;
import service.impl.UserServiceImpl;

import javax.management.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// user控制层
@WebServlet(name="UserServlet",urlPatterns = "/jsp/user.do")
public class UserServlet extends HttpServlet {
    UserService userService=new UserServiceImpl();
    RoleService roleService=new RoleServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String method=req.getParameter("method");//获取页面标记
         if("query".equals(method)){
             query(req,resp);//报错，Alt+回车 再回车
         }else if("querys".equals(method)){
             querys(req,resp);
         }else if("getrolelist".equals(method)){
             getrolelist(req,resp);
         }else if("ucexist".equals(method)){
             ucexist(req,resp);
         }
    }
                //用户编码验证是否重复的ajax
    private void ucexist(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String userCode=req.getParameter("userCode");
        boolean flag=userService.userTrueOrFalse(userCode);
        Map<String,String> map=new HashMap<>();
        if(!flag){//代表可用
            map.put("userCode","exist");
        }else{
            map.put("userCode","ok");
        }
        PrintWriter out=resp.getWriter();
        out.print(JSONArray.toJSONString(map));
        out.flush();
        out.close();
    }

    //添加页面用户角色下拉框的ajax
    private void getrolelist(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<Role> rList=roleService.getRoleList();
        PrintWriter out=resp.getWriter();
        out.print(JSONArray.toJSONString(rList));
        out.flush();
        out.close();
    }

    /*根据用户名或角色查询*/
    private void querys(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user=new User();
        String name=req.getParameter("queryname");
        System.out.print("name="+name+"----------------");
        user.setUserName(name);
        String roleid=req.getParameter("queryUserRole");
        if(roleid!=null){
            user.setUserRole(Integer.valueOf(roleid));
        }
        List<User> uList= userService.getUserListByNameAndRole(user);
        List<Role> rList=roleService.getRoleList();
        req.setAttribute("userList",uList);
        req.setAttribute("roleList",rList);
        req.getRequestDispatcher("userlist.jsp").forward(req,resp);
    }

    // 用户管理首页
    private void query(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> uList= userService.getUserList();
        List<Role> rList=roleService.getRoleList();
        req.setAttribute("roleList",rList);
        req.setAttribute("userList",uList);
        req.getRequestDispatcher("userlist.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
