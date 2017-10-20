package com.ysusoft.knowledge.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ysusoft.knowledge.bean.Law;

/**
 *  知识库-法律法规
 * @author songyy
 * @date 2017年8月15日
 */

public interface LawDao extends JpaRepository<Law, Long> {

	 /**
	  * 查法律法规所有
	  * @return
	  */
	 List<Law> findByLawTitleNotNull();
}
