package com.bvvy.sys.auth.service.impl;

import com.bvvy.basic.builder.PageBuilder;
import com.bvvy.basic.util.Commutil;
import com.bvvy.basic.util.SecurityUtil;
import com.bvvy.sys.auth.model.Role;
import com.bvvy.sys.auth.model.User;
import com.bvvy.sys.auth.repo.RoleRepository;
import com.bvvy.sys.auth.repo.UserRepository;
import com.bvvy.sys.auth.service.IRoleService;
import com.bvvy.sys.constant.SysFinalValue;
import com.bvvy.sys.exception.SysException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * Created by bvvy on 2017/5/18.
 */
@Service
public class RoleServiceImpl implements IRoleService {

    @Resource
    private RoleRepository roleRepository;

    @Resource
    private UserRepository userRepository;


    @Override
    public Page<Role> page(Integer page, Integer size) {
        return roleRepository.findAll(PageBuilder.generate(page, size));
    }

    @Override
    public void save(Role role) {
        roleRepository.save(role);
    }

    @Override
    public void delete(Integer[] ids) {
        for (Integer id : ids) {
            roleRepository.delete(id);
        }
    }

    @Override
    public Role get(int id) {
        return roleRepository.getOne(id);
    }


    @Override
    public void addRoleUsers(Integer[] userIds, Integer roleId) {
        roleRepository.deleteRoleUsers(roleId);
        Role role = roleRepository.getOne(roleId);
        if (Commutil.isNotEmpty(userIds)) {
            for (Integer userId : userIds) {
                User user = userRepository.getOne(userId);
                roleRepository.addRoleUser(user, role);
            }
        }
    }

    @Override
    public List<User> listRoleUsers(int roleId) {
        return roleRepository.listRoleUsers(roleId);
    }

    @Override
    public List<Integer> listRoleUserIds(int roleId) {
        return roleRepository.listRoleUserIds(roleId);
    }
}
