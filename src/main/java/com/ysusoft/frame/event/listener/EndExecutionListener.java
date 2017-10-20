package com.ysusoft.frame.event.listener;

import java.util.Date;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.ysusoft.frame.event.bean.ExecutionBean;
import com.ysusoft.frame.event.dao.ExecutionDao;
import com.ysusoft.frame.event.utils.SpringContextHolder;

/**
 * 
* @ClassName: EndExecutionListener 
* @Description: 结束案件时，由工作流引擎调用本类，更新相应的业务附属信息。
* @author Wuf
* @date 2017年6月14日 
*
 */
public class EndExecutionListener implements ExecutionListener,ApplicationContextAware {

	private static final long serialVersionUID = 1L;
	
	@Override
	public void notify(DelegateExecution delegateExecution) throws Exception {
		
		ExecutionDao executionDao = SpringContextHolder.getBean(ExecutionDao.class);
		//案件编号
		String processInstanceId = delegateExecution.getProcessInstanceId();
		ExecutionBean executionBean = executionDao.findByprocessId(processInstanceId);
		executionBean.setEndDate(new Date());
		executionBean.setClosed("1");
		
		//TODO:调用短信接口
		
		//TODO:调用微信接口
		
		//调试阶段使用
		System.out.println("案件结束了，回复给用户的信息是："+executionBean.getFeedBack());
		
		//更新结束时间
		executionDao.updateExecutionById(executionBean.getExecutionId());
	}

	@Override
	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {
		
	}
}
