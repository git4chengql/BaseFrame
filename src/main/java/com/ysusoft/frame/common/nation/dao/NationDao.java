package com.ysusoft.frame.common.nation.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ysusoft.frame.common.nation.bean.Nation;

/**
 * @author songyy
 * @date 2017年6月14日 下午2:41:03
 */
public interface NationDao extends JpaRepository<Nation,Long> {
	
	/**
	 * 得到基础信息
	 */
	public List<Nation> findByNationNameNotNull();
	

}
