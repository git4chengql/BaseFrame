package com.ysusoft.frame.common.edu.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ysusoft.frame.common.edu.bean.Edu;

/**
 * @author songyy
 * @date 2017年6月14日 下午2:41:03
 */
public interface EduDao extends JpaRepository<Edu,Long> {
	
	/**
	 * 得到基础信息
	 */
	public List<Edu> findByEduNameNotNull();
	

}
