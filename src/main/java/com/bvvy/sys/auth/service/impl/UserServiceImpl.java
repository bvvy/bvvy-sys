package com.bvvy.sys.auth.service.impl;

import com.bvvy.basic.builder.PageBuilder;
import com.bvvy.basic.util.Commutil;
import com.bvvy.basic.util.SecurityUtil;
import com.bvvy.sys.auth.model.Role;
import com.bvvy.sys.auth.model.User;
import com.bvvy.sys.auth.model.UserRole;
import com.bvvy.sys.auth.repo.RoleRepository;
import com.bvvy.sys.auth.repo.UserRepository;
import com.bvvy.sys.auth.service.IUserService;
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
public class UserServiceImpl implements IUserService {

    @Resource
    private UserRepository userRepository;

    @Resource
    private RoleRepository roleRepository;


    @Override
    public Page<User> page(Integer page, Integer size) {
        return userRepository.findAll(PageBuilder.generate(page, size));
    }

    @Override
    public void save(User user) {
        try {
            if (Commutil.isEmpty(user.getId())) {
                User tu = userRepository.getByUsername(user.getUsername());
                if (Commutil.isNotEmpty(tu)) throw new SysException(SysFinalValue.ADD_SAME_USER_ERROR);
                user.setPassword(SecurityUtil.md5(user.getUsername(), user.getPassword()));
            }
            userRepository.save(user);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer[] ids) {
        userRepository.delete(ids);
    }

    @Override
    public User get(Integer id) {
        return userRepository.findOne(id);
    }

    @Override
    public List<Role> listUserRoles(int userId) {
        return userRepository.listUserRoles(userId);
    }

    @Override
    public List<Integer> listUserRoleIds(int userId) {
        return userRepository.listUserRoleIds(userId);
    }

    @Override
    public UserRole loadUserRole(int userId, int roleId) {
        return userRepository.loadUserRole(userId, roleId);
    }


    @Override
    public void deleteUserRoles(int uid) {
        userRepository.deleteUserRoles(uid);
    }

    @Override
    public void deleteUserRole(int uid, int rid) {
        userRepository.deleteUserRole(uid, rid);
    }

    @Override
    public User login(String username, String password) {
        User user = userRepository.getByUsername(username);
        try {
            if (Commutil.isEmpty(user)) throw new SysException(SysFinalValue.LOGIN_ERROR_MSG);
            System.out.println(SecurityUtil.md5(username, password));
            System.out.println(user.getPassword());

            if (SecurityUtil.md5(username, password).equals(user.getPassword())) {
                return user;
            } else {
                throw new SysException(SysFinalValue.LOGIN_ERROR_MSG);
            }
        } catch (NoSuchAlgorithmException e) {
            throw new SysException(SysFinalValue.LOGIN_ERROR_MSG);
        }
    }
}
