package com.ysusoft.knowledge.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ysusoft.knowledge.bean.Question;

/**
 *  知识库-常见问题
 * @author songyy
 * @date 2017年8月15日
 */

public interface QuestionDao extends JpaRepository<Question, Long> {

	 /**
	  * 查常见问题表所有
	  * @return
	  */
	 List<Question> findByKnowTitleNotNull();
}
