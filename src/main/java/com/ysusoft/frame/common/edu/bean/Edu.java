package com.ysusoft.frame.common.edu.bean;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author songyy
 * @date 2017年6月14日 基础信息 
 */

@Entity
@Table(name = "t_Base_Edu")
public class Edu {

	@Id
	@GeneratedValue
	private int eduId;
	private String eduName;
	private String eduCode;
	
	
	public int getEduId() {
		return eduId;
	}
	public void setEduId(int eduId) {
		this.eduId = eduId;
	}
	public String getEduName() {
		return eduName;
	}
	public void setEduName(String eduName) {
		this.eduName = eduName;
	}
	public String getEduCode() {
		return eduCode;
	}
	public void setEduCode(String eduCode) {
		this.eduCode = eduCode;
	}
	
}
