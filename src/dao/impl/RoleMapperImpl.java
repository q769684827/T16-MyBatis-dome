package dao.impl;

import dao.RoleMapper;
import org.apache.ibatis.session.SqlSession;
import pojo.Role;
import util.MyBatisUtil;

import java.util.List;

public class RoleMapperImpl implements RoleMapper {
    private SqlSession sqlSession= MyBatisUtil.getSession();
    @Override
    public List<Role> getRoleList() {
        return sqlSession.getMapper(RoleMapper.class).getRoleList();
    }
}
