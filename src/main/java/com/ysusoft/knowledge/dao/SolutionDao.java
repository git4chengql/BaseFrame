package com.ysusoft.knowledge.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ysusoft.knowledge.bean.Solution;

/**
 *  知识库-解决措施
 * @author songyy
 * @date 2017年8月16日
 */

public interface SolutionDao extends JpaRepository<Solution, Long> {

	 /**
	  * 查所有
	  * @return
	  */
	 List<Solution> findBySolutTitleNotNull();
}
