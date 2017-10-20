package com.ysusoft.knowledge.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 知识库-行业分析报告
 * @author songyy
 */
@Entity
@Table(name="t_Knowledge_Report")
public class Report {
	@Id
	@GeneratedValue
	private Integer reportId;
	//
	private String reportTitle;

	private String reportContent;

	public Integer getReportId() {
		return reportId;
	}

	public void setReportId(Integer reportId) {
		this.reportId = reportId;
	}

	public String getReportTitle() {
		return reportTitle;
	}

	public void setReportTitle(String reportTitle) {
		this.reportTitle = reportTitle;
	}

	public String getReportContent() {
		return reportContent;
	}

	public void setReportContent(String reportContent) {
		this.reportContent = reportContent;
	}

}
