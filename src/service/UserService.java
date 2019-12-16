package service;

import pojo.User;

import java.util.List;

//user服务层
public interface UserService {
    //登录
    User login(User user);

    //查询所有用户
    List<User> getUserList();

    /*根据用户名或角色查询*/
    List<User> getUserListByNameAndRole(User user);

        //查询用户是否存在
    boolean userTrueOrFalse(String userCode);
}
