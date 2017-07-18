package com.bvvy.sys.auth.model;

import javax.persistence.*;

/**
 * 用户和角色的关联表
 * @author Konghao
 *
 */
@Entity
@Table(name="t_user_role")
public class UserRole {
	private Integer id;
	private User user;
	private Role role;
	
	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@ManyToOne
	@JoinColumn(name="uid")
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	@ManyToOne
	@JoinColumn(name="rid")
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
}
