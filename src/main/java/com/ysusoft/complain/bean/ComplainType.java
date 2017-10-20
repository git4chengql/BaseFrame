package com.ysusoft.complain.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 投诉类型
 * @author Wuf
 */
@Entity
@Table(name="t_Complain_Type")
public class ComplainType {

	@Id
	@GeneratedValue
	private Integer complainTypeId;
	
	/**
	 * 大类，如:服务质量：1，卫生状况：2，欺客宰客：3，门票价格：4，其他：5,等.
	 */
	private String type;
	
	/**
	 * 阶段，如1，2，3
	 */
	private String stage;
	
	/**
	 * 时限总计，如60（小时）
	 */
	private Integer totle;
	
	/**
	 * 危险级时限，如 5（小时）
	 */
	private Integer danger;
	
	/**
	 * 警告级时限，如10（小时）
	 */
	private Integer notice;
	
	/**
	 * 正常级时限，如60（小时）
	 */
	private Integer normal;

	public Integer getComplainTypeId() {
		return complainTypeId;
	}

	public void setComplainTypeId(Integer complainTypeId) {
		this.complainTypeId = complainTypeId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStage() {
		return stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}

	public Integer getTotle() {
		return totle;
	}

	public void setTotle(Integer totle) {
		this.totle = totle;
	}

	public Integer getDanger() {
		return danger;
	}

	public void setDanger(Integer danger) {
		this.danger = danger;
	}

	public Integer getNotice() {
		return notice;
	}

	public void setNotice(Integer notice) {
		this.notice = notice;
	}

	public Integer getNormal() {
		return normal;
	}

	public void setNormal(Integer normal) {
		this.normal = normal;
	}
	
}
