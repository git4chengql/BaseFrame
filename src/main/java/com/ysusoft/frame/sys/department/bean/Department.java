package com.ysusoft.frame.sys.department.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author qlcheng
 * @date 2017年4月10日 下午2:39:02
 * 部门信息表
 */
@Entity
@Table(name="t_Sys_Department")
public class Department {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int did;
	private String dName;
	private int pdid;
	public int getDid() {
		return did;
	}
	public void setDid(int did) {
		this.did = did;
	}
	public String getdName() {
		return dName;
	}
	public void setdName(String dName) {
		this.dName = dName;
	}
	public int getPdid() {
		return pdid;
	}
	public void setPdid(int pdid) {
		this.pdid = pdid;
	}
	
}
