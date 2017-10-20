package com.ysusoft.knowledge.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ysusoft.knowledge.bean.Report;

/**
 *  知识库-行业分析报告
 * @author songyy
 * @date 2017年8月16日
 */

public interface ReportDao extends JpaRepository<Report, Long> {

	 /**
	  * 查所有
	  * @return
	  */
	 List<Report> findByReportTitleNotNull();
}
