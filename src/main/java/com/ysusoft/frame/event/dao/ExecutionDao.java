package com.ysusoft.frame.event.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.ysusoft.complain.bean.Complain;
import com.ysusoft.frame.event.bean.ExecutionBean;

public interface ExecutionDao  extends JpaRepository<ExecutionBean, Long> {

	/**
	 * 
	* @Title: findByprocessId 
	* @Description: 根据processId获取bean
	* @param @param processId
	* @param @return    设定文件 
	* @return ExecutionBean    返回类型 
	* @throws
	 */
	public ExecutionBean findByprocessId(String processId);
	
	@Modifying
	@Query(value = "update t_event_execution t set t.closed=1,t.enddate=now() where t.executionId=?1",nativeQuery = true)
	public int updateExecutionById(int executionId);
	
	/**
	 *  
	* @Title: findByprocessIdIn 
	* @Description: 通过案件ID集合获取所有案件
	* @param @param ids
	* @param @return    设定文件 
	* @return List<ExecutionBean>    返回类型 
	* @throws
	 */
	public Page<ExecutionBean> findByprocessIdIn(List<String> ids,Pageable pageable);
	
	/**
	 * 
	* @Title: findByprocessIdInAndComplainIdIn 
	* @Description: 通过案件ID集合和特定的案件合计获取所有案件
	* @param @param ids
	* @param @param complains
	* @param @param pageable
	* @param @return    设定文件 
	* @return Page<ExecutionBean>    返回类型 
	* @throws
	 */
	public Page<ExecutionBean> findByprocessIdInAndComplainIdIn(List<String> ids,List<Complain> complains,Pageable pageable);
	

	/**
	 * 
	* @Title: findByprocessIdInAndClosedIn 
	* @Description: 通过案件ID集合获取所有已完成案件
	* @param @param ids
	* @param @param closeds
	* @param @param pageable
	* @param @return    设定文件 
	* @return Page<ExecutionBean>    返回类型 
	* @throws
	 */
	public Page<ExecutionBean> findByprocessIdInAndClosedIn(List<String> ids,List<String> closeds,Pageable pageable);
	
	/**
	 * 
	* @Title: findByprocessIdInAndComplainIdInAndClosedIn 
	* @Description: 通过案件ID集合和特定的案件合计获取所有已完成案件
	* @param @param ids
	* @param @param complains
	* @param @param closeds
	* @param @param pageable
	* @param @return    设定文件 
	* @return Page<ExecutionBean>    返回类型 
	* @throws
	 */
	public Page<ExecutionBean> findByprocessIdInAndComplainIdInAndClosedIn(List<String> ids,List<Complain> complains,List<String> closeds,Pageable pageable);
	
}
