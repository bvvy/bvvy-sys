package com.bvvy.sys.auth.repo;



import com.bvvy.basic.repo.BaseRepository;
import com.bvvy.sys.auth.model.Role;
import com.bvvy.sys.auth.model.User;
import com.bvvy.sys.auth.model.UserRole;
import com.bvvy.sys.auth.repo.custom.RoleRepositoryCustom;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends BaseRepository<User,Integer> {

	public User getByUsername(String username);

	/**
	 * 获取用户的所有角色信息
	 *
	 * @param userId
	 * @return
	 */
	@Query("select ur.role from UserRole ur where ur.user.id=?1")
	public List<Role> listUserRoles(int userId);

	/**
	 * 获取用户的所有角色的id
	 *
	 * @param userId
	 * @return
	 */
	@Query("select ur.role.id from UserRole ur where ur.user.id = ?1")
	public List<Integer> listUserRoleIds(int userId);

	/**
	 * 根据用户和角色获取用户角色的关联对象
	 *
	 * @param userId
	 * @param roleId
	 * @return
	 */
	@Query("select ur from UserRole ur left join fetch ur.role left join fetch  ur.user where ur.user.id=?1 and ur.role.id=?2")
	public UserRole loadUserRole(int userId, int roleId);



	/**
	 * 删除用户的角色信息
	 *
	 * @param uid
	 */
	@Query("delete from UserRole ur where ur.user.id=?1")
	public void deleteUserRoles(int uid);

	/**
	 * 删除用户角色对象
	 *
	 * @param uid
	 * @param rid
	 */
	@Query("delete from UserRole ur where ur.user.id=?1 and ur.role.id=?2")
	public void deleteUserRole(int uid, int rid);


}
