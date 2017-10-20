package com.ysusoft.frame.common.nation.bean;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author songyy
 * @date 2017年6月14日 基础信息 
 */

@Entity
@Table(name = "t_Base_Nation")
public class Nation {

	@Id
	@GeneratedValue
	private int nationId;
	private String nationName;
	private String nationCode;
	
	
	public int getNationId() {
		return nationId;
	}
	public void setNationId(int nationId) {
		this.nationId = nationId;
	}
	public String getNationName() {
		return nationName;
	}
	public void setNationName(String nationName) {
		this.nationName = nationName;
	}
	public String getNationCode() {
		return nationCode;
	}
	public void setNationCode(String nationCode) {
		this.nationCode = nationCode;
	}
	
	
}
