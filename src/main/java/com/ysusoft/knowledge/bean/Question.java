package com.ysusoft.knowledge.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 知识库-常见问题
 * @author songyy
 */
@Entity
@Table(name="t_Knowledge_Question")
public class Question {
	@Id
	@GeneratedValue
	private Integer knowId;
	//
	private String knowTitle;

	private String knowContent;

	public Integer getKnowId() {
		return knowId;
	}

	public void setKnowId(Integer knowId) {
		this.knowId = knowId;
	}

	public String getKnowTitle() {
		return knowTitle;
	}

	public void setKnowTitle(String knowTitle) {
		this.knowTitle = knowTitle;
	}

	public String getKnowContent() {
		return knowContent;
	}

	public void setKnowContent(String knowContent) {
		this.knowContent = knowContent;
	}

}
