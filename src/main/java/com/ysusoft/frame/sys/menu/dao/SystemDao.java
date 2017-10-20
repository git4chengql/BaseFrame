package com.ysusoft.frame.sys.menu.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ysusoft.frame.sys.menu.bean.AllSystem;


/**
 * @author songyy
 * @date 2017年9月6日
 */
public interface SystemDao extends JpaRepository<AllSystem,Long> {
	public List<AllSystem> findBysystemNameNotNull();
	  
}
