package com.bvvy.sys.auth.repo.custom;

import com.bvvy.sys.auth.model.Role;
import com.bvvy.sys.auth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by bvvy on 2017/5/23.
 */
public interface RoleRepositoryCustom {
    /**
     * 添加用户角色对象
     * @param user
     * @param role
     */
    @Transactional
    void addRoleUser(User user, Role role);
}
