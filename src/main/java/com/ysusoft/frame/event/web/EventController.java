package com.ysusoft.frame.event.web;

import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.impl.interceptor.Command;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ysusoft.frame.event.bean.ExecutionBean;
import com.ysusoft.frame.event.bean.TaskBean;
import com.ysusoft.frame.event.service.EventService;
import com.ysusoft.frame.event.utils.HistoryProcessInstanceDiagramCmd;
import com.ysusoft.frame.sys.base.bean.Grid;
import com.ysusoft.frame.sys.base.bean.ResultInfo;
import com.ysusoft.frame.sys.exception.SysException;

/**
 * 
* @ClassName: EventController 
* @Description: 业务驱动
* @author Wuf
* @date 2017年6月5日 
*
 */
@Controller
@RequestMapping("event/")
public class EventController {

	@Autowired
	private EventService eventService;

	/**
	 * 
	* @Title: deployProcess 
	* @Description: 启动工作流引擎
	* @param @param name 自定义加载名称
	* @param @param fileName 设计文件名称
	* @param @return
	* @param @throws SysException    设定文件 
	* @return boolean    返回类型 
	* @throws
	 */
	@RequestMapping("deployProcess")
	@ResponseBody
	public boolean deployProcess(String name,String fileName) throws SysException{
		return eventService.deployProcess(name, fileName);
	}
	
	/**
	 * 分页查询所有执行的任务
	 * @param page 页码
	 * @param pageSize 大小
	 * @return
	 */
	@RequestMapping("allTask")
	@ResponseBody
	public Grid<ExecutionBean> getallTask(HttpSession httpSession,@RequestParam("backInfo")String backInfo,@RequestParam("page")int page,@RequestParam("pageSize")int pageSize) throws SysException{
		return eventService.getTask(httpSession, backInfo,page-1, pageSize);
	}
	
	/**
	 * 分页查询所有完成的任务
	 * @param page 页码
	 * @param pageSize 大小
	 * @return
	 */
	@RequestMapping("allTaskEnd")
	@ResponseBody
	public Grid<ExecutionBean> getTaskForEnd(HttpSession httpSession,@RequestParam("backInfo")String backInfo,@RequestParam("page")int page,@RequestParam("pageSize")int pageSize) throws SysException{
		return eventService.getTaskForEnd(httpSession, backInfo,page-1, pageSize);
	}
	
	/**
	 * 
	* @Title: getprocess 
	* @Description: 获取某案件的详细历史信息
	* @param @param executionId 案件ID
	* @param @return    设定文件 
	* @return ResultInfo<TaskBean>    返回类型 
	* @throws
	 */
	@RequestMapping("getProcess")
	@ResponseBody
	public ResultInfo<TaskBean> getProcess(@RequestParam("executionId")String executionId) throws SysException{
		return eventService.getProcess(executionId);
	}
	

	/**
	 * 
	* @Title: completeTask 
	* @Description: 通用完成一个工作流任务
	* @param @param httpSession 用于获取session中的用户信息
	* @param @param taskBean 任务信息
	* @param @param otype 工作流下一步路径
	* @param @return
	* @param @throws SysException    设定文件 
	* @return ResultInfo<Object>    返回类型 
	* @throws
	 */
	@RequestMapping("completeTask")
	@ResponseBody
	public ResultInfo<Object> completeTask(HttpSession httpSession,TaskBean taskBean,String otype) throws SysException{
		return eventService.completeTask(httpSession, taskBean, otype);
	}
	
	/**
	 * 
	* @Title: traceImage 
	* @Description: 绘制历史流程图
	* @param @param req
	* @param @param response
	* @param @param processInstanceId 案件ID
	* @param @throws Exception    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	@RequestMapping("traceImage")
	@ResponseBody
    public void traceImage(HttpServletRequest req,HttpServletResponse response,@RequestParam("processInstanceId")String processInstanceId) throws Exception {
		Command<InputStream> cmd = new HistoryProcessInstanceDiagramCmd(
                processInstanceId);

		ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine(); 
        InputStream is = processEngine.getManagementService().executeCommand(cmd);
        int len = 0;
        byte[] b = new byte[1024];

        while ((len = is.read(b, 0, 1024)) != -1) {
        	response.getOutputStream().write(b, 0, len);
        }
    }
}
