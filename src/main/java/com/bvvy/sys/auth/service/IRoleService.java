package com.bvvy.sys.auth.service;

import com.bvvy.sys.auth.model.Role;
import com.bvvy.sys.auth.model.User;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by bvvy on 2017/5/18.
 */
public interface IRoleService {

    public Page<Role> page(Integer page, Integer size);

    public void save(Role role);

    public void delete(Integer[] ids);

    public Role get(int id);


    /**
     * 配置角色中的用户
     */
    void addRoleUsers(Integer[] userIds, Integer roleId);

    List<User> listRoleUsers(int roleId);

    List<Integer> listRoleUserIds(int roleId);
}
