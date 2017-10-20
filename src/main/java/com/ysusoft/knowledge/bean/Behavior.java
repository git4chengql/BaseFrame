package com.ysusoft.knowledge.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 知识库-不文明行为记录
 * @author songyy
 */
@Entity
@Table(name="t_Knowledge_Behavior")
public class Behavior {
	@Id
	@GeneratedValue
	private Integer behvId;
	//
	private String behvTitle;

	private String behvContent;

	public Integer getBehvId() {
		return behvId;
	}

	public void setBehvId(Integer behvId) {
		this.behvId = behvId;
	}

	public String getBehvTitle() {
		return behvTitle;
	}

	public void setBehvTitle(String behvTitle) {
		this.behvTitle = behvTitle;
	}

	public String getBehvContent() {
		return behvContent;
	}

	public void setBehvContent(String behvContent) {
		this.behvContent = behvContent;
	}

}
