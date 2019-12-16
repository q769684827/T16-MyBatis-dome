package service.impl;

import dao.RoleMapper;
import dao.impl.RoleMapperImpl;
import pojo.Role;
import service.RoleService;

import java.util.List;

public class RoleServiceImpl implements RoleService {
    RoleMapper roleMapper=new RoleMapperImpl();

    @Override
    public List<Role> getRoleList() {
        return roleMapper.getRoleList();
    }
}
