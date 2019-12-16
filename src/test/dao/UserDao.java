package test.dao;

import pojo.User;

import java.util.List;
import java.util.Map;

//dao层
public interface UserDao {
    //查询用户表的记录数
    Integer getUserCount();
    //查询所有用户信息
    List<User> getUserList();
    //根据用户名模糊查询用户
    List<User> getUserListLikeByName(String name);
    //多条件查询之对象入参
    List<User> getUserListParametersByUser(User user);
    //多条件查询之以Map入参
    List<User> getUserListParametersByMap(Map<String,Object> maps);
}
