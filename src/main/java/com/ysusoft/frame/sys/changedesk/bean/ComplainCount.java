package com.ysusoft.frame.sys.changedesk.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="t_complaindate")
public class ComplainCount {
	@Id
	@GeneratedValue
	private Integer id;
	private String complaindate;
	private Integer comcount;
	public String getComplaindate() {
		return complaindate;
	}
	public void setComplaindate(String complaindate) {
		this.complaindate = complaindate;
	}
	public Integer getComcount() {
		return comcount;
	}
	public void setComcount(Integer comcount) {
		this.comcount = comcount;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	

	
}
