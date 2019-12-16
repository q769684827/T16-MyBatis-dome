package service.impl;

import dao.UserMapper;
import dao.impl.UserMapperImpl;
import org.apache.ibatis.session.SqlSession;
import pojo.User;
import service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    UserMapper userMapper=new UserMapperImpl();

    @Override
    public User login(User user)
    {
        return userMapper.login(user);
    }

    @Override
    public List<User> getUserList() {

        return userMapper.getUserList();
    }

    @Override
    public List<User> getUserListByNameAndRole(User user) {
        return userMapper.getUserListByNameAndRole(user);
    }

    @Override
    public boolean userTrueOrFalse(String userCode) {
       User user=userMapper.getUserByCode(userCode);
       if(user==null){
           return true;
       }
        return false;
    }
}
