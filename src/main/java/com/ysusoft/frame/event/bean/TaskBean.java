package com.ysusoft.frame.event.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 
 * @ClassName: TaskBean
 * @Description: 任务表
 * @author Wuf
 * @date 2017年6月12日
 *
 */
@Entity
@Table(name = "t_event_task")
public class TaskBean {

	@Id
	@GeneratedValue
	private Integer taskId;

	// 绑定案件
	@ManyToOne(optional = false)
	@JoinColumn(name = "executionId")
	private ExecutionBean executionId;

	// 创建时间
	@OrderBy(value = "createDate DESC")
	private Date createDate;

	// 结束时间
	private Date endDate;

	// 关联受理人员
	private Long userId;

	// 反馈意见
	private String remark;

	// 工作流中的任务ID
	private String wfTaskId;

	// 任务限制持续时间
	@Transient
	private Integer dueDate;

	// 暂存完成任务移交下个角色的角色ID
	@Transient
	private String roleId;

	// 反馈用户信息
	@Transient
	private String feedBack;

	// 操作人姓名
	@Transient
	private String userName;

	// 任务阶段
	@Transient
	private String stage;

	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	public ExecutionBean getExecutionId() {
		return executionId;
	}

	public void setExecutionId(ExecutionBean executionId) {
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

	public Integer getDueDate() {
		return dueDate;
	}

	public void setDueDate(Integer dueDate) {
		this.dueDate = dueDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getWfTaskId() {
		return wfTaskId;
	}

	public void setWfTaskId(String wfTaskId) {
		this.wfTaskId = wfTaskId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getFeedBack() {
		return feedBack;
	}

	public void setFeedBack(String feedBack) {
		this.feedBack = feedBack;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getStage() {
		return stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}
}
