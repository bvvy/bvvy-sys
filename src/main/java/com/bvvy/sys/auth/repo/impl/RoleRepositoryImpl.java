package com.bvvy.sys.auth.repo.impl;

import com.bvvy.sys.auth.model.Role;
import com.bvvy.sys.auth.model.User;
import com.bvvy.sys.auth.model.UserRole;
import com.bvvy.sys.auth.repo.custom.RoleRepositoryCustom;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by bvvy on 2017/5/23.
 */
@Repository
public class RoleRepositoryImpl implements RoleRepositoryCustom {

    @Resource
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addRoleUser(User user, Role role) {
        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);
        entityManager.persist(userRole);
    }

}
