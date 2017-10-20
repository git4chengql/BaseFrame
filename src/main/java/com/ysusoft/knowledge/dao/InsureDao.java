package com.ysusoft.knowledge.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ysusoft.knowledge.bean.Insure;

/**
 *  知识库-保险知识
 * @author songyy
 * @date 2017年8月16日
 */

public interface InsureDao extends JpaRepository<Insure, Long> {

	 /**
	  * 查所有
	  * @return
	  */
	 List<Insure> findByInsureTitleNotNull();
}
