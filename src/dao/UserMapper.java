package dao;

import pojo.User;

import java.util.List;

//User的Dao层
public interface UserMapper {
    //登录
    User login(User user);

    //查询所有用户
    List<User> getUserList();

    //根据用户名和角色查询用户
    List<User> getUserListByNameAndRole(User user);

    //查询用户编码是否重复
    User getUserByCode(String userCode);
}
