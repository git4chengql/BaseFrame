package com.ysusoft.knowledge.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ysusoft.frame.sys.exception.SysException;
import com.ysusoft.knowledge.bean.Report;
import com.ysusoft.knowledge.dao.ReportDao;

/**
 * 知识库-行业分析报告
 * @author songyy
 * @date 2017年8月16日
 */

@Service
public class ReportService {
	
	@Autowired
	private ReportDao reportDao;

	public List<Report> getReport()throws SysException{
		List<Report> list = reportDao.findByReportTitleNotNull();
		return list;
	}
	
}
