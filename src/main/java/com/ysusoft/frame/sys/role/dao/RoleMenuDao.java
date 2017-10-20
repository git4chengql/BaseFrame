package com.ysusoft.frame.sys.role.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ysusoft.frame.sys.role.bean.RoleMenu;

public interface RoleMenuDao extends JpaRepository<RoleMenu, Integer>{

	/**
	 * 根据角色id删除菜单
	 * @param roleId
	 * @return
	 */
	@Transactional
	public int deleteByRoleId(int roleId);
}
