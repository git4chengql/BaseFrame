package com.ysusoft.knowledge.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 知识库-经典案例
 * @author songyy
 */
@Entity
@Table(name="t_Knowledge_Case")
public class Case {
	@Id
	@GeneratedValue
	private Integer caseId;
	//
	private String caseTitle;

	private String caseContent;

	public Integer getCaseId() {
		return caseId;
	}

	public void setCaseId(Integer caseId) {
		this.caseId = caseId;
	}

	public String getCaseTitle() {
		return caseTitle;
	}

	public void setCaseTitle(String caseTitle) {
		this.caseTitle = caseTitle;
	}

	public String getCaseContent() {
		return caseContent;
	}

	public void setCaseContent(String caseContent) {
		this.caseContent = caseContent;
	}

	
}
