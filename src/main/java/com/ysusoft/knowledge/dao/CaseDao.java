package com.ysusoft.knowledge.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ysusoft.knowledge.bean.Case;

/**
 *  知识库-经典案例
 * @author songyy
 * @date 2017年8月16日
 */

public interface CaseDao extends JpaRepository<Case, Long> {

	 /**
	  * 查所有
	  * @return
	  */
	 List<Case> findByCaseTitleNotNull();
}
