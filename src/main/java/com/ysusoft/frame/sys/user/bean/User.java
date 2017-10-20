package com.ysusoft.frame.sys.user.bean;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.ysusoft.frame.sys.role.bean.Role;

/**
 * @author qlcheng
 * @date 2017年4月10日 下午2:40:09
 */
@Entity
@Table(name = "t_Sys_User")
public class User {

	@Id
	@GeneratedValue
	private Long userId;

	@Column(length = 50, nullable = false)
	private String userName;
	private String loginName;
	private String password;
	@ManyToMany(cascade = CascadeType.PERSIST , 
			fetch = FetchType.EAGER)
	@JoinTable(name = "t_User_Role", 
			joinColumns = {@JoinColumn(name = "userId", referencedColumnName = "userId")}, 
            inverseJoinColumns = {@JoinColumn(name = "roleId", referencedColumnName = "roleId")})
	private List<Role> roles = new ArrayList<Role>(); 

	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	
}
