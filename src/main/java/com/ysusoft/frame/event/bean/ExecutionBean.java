package com.ysusoft.frame.event.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.ysusoft.complain.bean.Complain;

/**
 * 
* @ClassName: ExecutionBean 
* @Description: 案件表
* @author Wuf
* @date 2017年6月12日 
*
 */
@Entity
@Table(name="t_event_execution")
public class ExecutionBean {

	@Id
	@GeneratedValue
	private Integer executionId;

	//创建时间
	@OrderBy(value = "createDate DESC")
	private Date createDate;
	
	//结束时间
	private Date endDate;
	
	//案件记录
	@OneToOne(optional = false)
	@JoinColumn(name = "complainId")
	private Complain complainId;
	
	//是否结案，0：未结案，1：已结案
	private String closed;
	
	//案件投诉类型（某状态某时限）
	private String comlainType;
	
	//工作流中的案件ID
	private String processId;
	
	//反馈用户信息
	private String feedBack;
	
	//不持久化，暂存当前案件的最新任务ID
	@Transient
	private String currentTask;
	
	//提示类型，对应ComplainType中的类型，1：danger，2：notice，3：normal
	@Transient
	private String cueType ;
	
	//用于投诉受理页面中的展示传递需要。存放当前案件所在的阶段（如受理平台（受理））
	@Transient
	private String stageName ;
	
	public Integer getExecutionId() {
		return executionId;
	}

	public void setExecutionId(Integer executionId) {
		this.executionId = executionId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Complain getComplainId() {
		return complainId;
	}

	public void setComplainId(Complain complainId) {
		this.complainId = complainId;
	}

	public String getProcessId() {
		return processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}

	public String getCurrentTask() {
		return currentTask;
	}

	public void setCurrentTask(String currentTask) {
		this.currentTask = currentTask;
	}

	public String getComlainType() {
		return comlainType;
	}

	public void setComlainType(String comlainType) {
		this.comlainType = comlainType;
	}

	public String getFeedBack() {
		return feedBack;
	}

	public void setFeedBack(String feedBack) {
		this.feedBack = feedBack;
	}

	public String getCueType() {
		return cueType;
	}

	public void setCueType(String cueType) {
		this.cueType = cueType;
	}

	public String getClosed() {
		return closed;
	}

	public void setClosed(String closed) {
		this.closed = closed;
	}

	public String getStageName() {
		return stageName;
	}

	public void setStageName(String stageName) {
		this.stageName = stageName;
	}

}
