package com.ysusoft.frame.event.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ysusoft.frame.event.bean.TaskBean;

public interface TaskDao extends JpaRepository<TaskBean, Long>{


	/**
	 * 
	* @Title: findByexecutionId 
	* @Description: 根据案件ID或者所有任务
	* @param @param executionId
	* @param @return    设定文件 
	* @return TaskBean    返回类型 
	* @throws
	 */
	@Query(value = "select * from t_event_task t where t.executionId = ?1",nativeQuery = true)
	public List<TaskBean> findAllByExecutionId(String executionId);
	
	/**
	 * 
	* @Title: findByUserId 
	* @Description: 获取所有该受理人员受理的任务
	* @param @param userId
	* @param @return    设定文件 
	* @return List<TaskBean>    返回类型 
	* @throws
	 */
	@Query(value = "select * from t_event_task t where t.userid = ?1",nativeQuery = true)
	public List<TaskBean> findByUserId(Long userId);
}
