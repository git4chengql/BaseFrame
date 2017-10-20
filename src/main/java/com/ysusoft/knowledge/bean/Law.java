package com.ysusoft.knowledge.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 知识库-法律法规
 * @author songyy
 */
@Entity
@Table(name="t_Knowledge_Law")
public class Law {
	@Id
	@GeneratedValue
	private Integer lawId;
	//
	private String lawTitle;

	private String lawContent;

	public Integer getLawId() {
		return lawId;
	}

	public void setLawId(Integer lawId) {
		this.lawId = lawId;
	}

	public String getLawTitle() {
		return lawTitle;
	}

	public void setLawTitle(String lawTitle) {
		this.lawTitle = lawTitle;
	}

	public String getLawContent() {
		return lawContent;
	}

	public void setLawContent(String lawContent) {
		this.lawContent = lawContent;
	}

}
