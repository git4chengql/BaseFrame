package com.ysusoft.frame.sys.user.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ysusoft.frame.sys.user.bean.UserMenu;

public interface UserMenuDao extends JpaRepository<UserMenu, Integer>{

	/**
	 * 根据角色id删除菜单
	 * @param roleId
	 * @return
	 */
	@Transactional
	public int deleteByUserId(int userId);
}
