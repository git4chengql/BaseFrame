package com.ysusoft.knowledge.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 知识库-解决措施
 * @author songyy
 */
@Entity
@Table(name="t_Knowledge_Solution")
public class Solution {
	@Id
	@GeneratedValue
	private Integer solutId;
	//
	private String solutTitle;

	private String solutContent;

	public Integer getSolutId() {
		return solutId;
	}

	public void setSolutId(Integer solutId) {
		this.solutId = solutId;
	}

	public String getSolutTitle() {
		return solutTitle;
	}

	public void setSolutTitle(String solutTitle) {
		this.solutTitle = solutTitle;
	}

	public String getSolutContent() {
		return solutContent;
	}

	public void setSolutContent(String solutContent) {
		this.solutContent = solutContent;
	}

}
