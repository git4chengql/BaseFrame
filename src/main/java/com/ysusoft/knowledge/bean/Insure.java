package com.ysusoft.knowledge.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 知识库-保险知识
 * @author songyy
 */
@Entity
@Table(name="t_Knowledge_Insure")
public class Insure {
	@Id
	@GeneratedValue
	private Integer insureId;
	//
	private String insureTitle;

	private String insureContent;

	public Integer getInsureId() {
		return insureId;
	}

	public void setInsureId(Integer insureId) {
		this.insureId = insureId;
	}

	public String getInsureTitle() {
		return insureTitle;
	}

	public void setInsureTitle(String insureTitle) {
		this.insureTitle = insureTitle;
	}

	public String getInsureContent() {
		return insureContent;
	}

	public void setInsureContent(String insureContent) {
		this.insureContent = insureContent;
	}

}
