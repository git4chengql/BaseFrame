package com.ysusoft.knowledge.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ysusoft.knowledge.bean.Standard;

/**
 *  知识库-行业标准
 * @author songyy
 * @date 2017年8月15日
 */

public interface StandardDao extends JpaRepository<Standard, Long> {

	 /**
	  * 查所有
	  * @return
	  */
	 List<Standard> findByStandTitleNotNull();
}
