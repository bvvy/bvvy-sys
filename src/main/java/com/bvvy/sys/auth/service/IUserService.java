package com.bvvy.sys.auth.service;

import com.bvvy.sys.auth.model.Role;
import com.bvvy.sys.auth.model.User;
import com.bvvy.sys.auth.model.UserRole;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;

/**
 * Created by ASUS on 2017/5/18.
 */
public interface IUserService {

    Page<User> page(Integer page,Integer size);

    public void save(User user);

    public void delete(Integer[] ids);

    public User get(Integer id);

    /**
     * 获取用户的所有角色信息
     *
     * @param userId
     * @return
     */
    public List<Role> listUserRoles(int userId);

    /**
     * 获取用户的所有角色的id
     *
     * @param userId
     * @return
     */
    public List<Integer> listUserRoleIds(int userId);

    /**
     * 根据用户和角色获取用户角色的关联对象
     *
     * @param userId
     * @param roleId
     * @return
     */
    public UserRole loadUserRole(int userId, int roleId);


    /**
     * 删除用户的角色信息
     *
     * @param uid
     */
    public void deleteUserRoles(int uid);

    /**
     * 删除用户角色对象
     *
     * @param uid
     * @param rid
     */
    public void deleteUserRole(int uid, int rid);

    public User login(String username,String password);




}
