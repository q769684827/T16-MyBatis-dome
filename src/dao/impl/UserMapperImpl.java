package dao.impl;

import dao.UserMapper;
import org.apache.ibatis.session.SqlSession;
import pojo.User;
import util.MyBatisUtil;

import java.util.List;

//实现类
public class UserMapperImpl implements UserMapper {
    private SqlSession sqlSession= MyBatisUtil.getSession();
    @Override
    public User login(User user) {

        return sqlSession.getMapper(UserMapper.class).login(user);
    }

    @Override
    public List<User> getUserList() {

        return sqlSession.getMapper(UserMapper.class).getUserList();
    }

    @Override
    public List<User> getUserListByNameAndRole(User user) {
        return sqlSession.getMapper(UserMapper.class).getUserListByNameAndRole(user);
    }

    @Override
    public User getUserByCode(String userCode) {
        return sqlSession.getMapper(UserMapper.class).getUserByCode(userCode);
    }
}
