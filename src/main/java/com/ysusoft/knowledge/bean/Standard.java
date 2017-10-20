package com.ysusoft.knowledge.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 知识库-行业标准
 * @author songyy
 */
@Entity
@Table(name="t_Knowledge_Standard")
public class Standard {
	@Id
	@GeneratedValue
	private Integer standId;
	//
	private String standTitle;

	private String standContent;

	public Integer getStandId() {
		return standId;
	}

	public void setStandId(Integer standId) {
		this.standId = standId;
	}

	public String getStandTitle() {
		return standTitle;
	}

	public void setStandTitle(String standTitle) {
		this.standTitle = standTitle;
	}

	public String getStandContent() {
		return standContent;
	}

	public void setStandContent(String standContent) {
		this.standContent = standContent;
	}

}
