package com.ysusoft.knowledge.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ysusoft.knowledge.bean.Behavior;

/**
 *  知识库-不文明行为记录
 * @author songyy
 * @date 2017年8月15日
 */

public interface BehaviorDao extends JpaRepository<Behavior, Long> {

	 /**
	  * 查所有
	  * @return
	  */
	 List<Behavior> findByBehvTitleNotNull();
}
