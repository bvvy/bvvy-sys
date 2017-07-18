package com.bvvy.sys.auth.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 角色对象
 * @author Konghao
 *
 */
@Entity
@Table(name="t_role")
public class Role implements Principal{
	public static final String PRINCIPAL_TYPE="role";
	private Integer id;
	private String name;
	private String sn;
	
	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
}
