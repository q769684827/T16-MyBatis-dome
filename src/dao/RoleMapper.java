package dao;

import pojo.Role;

import java.util.List;

//查询角色
public interface RoleMapper {
            //  查询角色列表
    List<Role> getRoleList();
}
