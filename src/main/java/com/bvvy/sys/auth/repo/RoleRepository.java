package com.bvvy.sys.auth.repo;


import com.bvvy.basic.repo.BaseRepository;
import com.bvvy.sys.auth.model.Role;
import com.bvvy.sys.auth.model.User;
import com.bvvy.sys.auth.repo.custom.RoleRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RoleRepository extends BaseRepository<Role, Integer>, RoleRepositoryCustom {


    /**
     * 删除角色中的全部用户
     * @param roleId
     */
    @Modifying
    @Transactional
    @Query("delete from UserRole ur where ur.role.id=?1")
    public void deleteRoleUsers(Integer roleId);

    /**
     * 根据角色id获取用户列表
     * @param roleId
     * @return
     */
    @Query("select ur.user from UserRole ur where ur.role.id=?1")
    public List<User> listRoleUsers(int roleId);
    /**
     * 根据角色id获取用户ids
     * @param roleId
     * @return
     */
    @Query("select ur.user.id from UserRole ur where ur.role.id=?1")
    public List<Integer> listRoleUserIds(Integer roleId);


}

