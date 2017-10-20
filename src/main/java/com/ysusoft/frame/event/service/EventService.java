package com.ysusoft.frame.event.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.activiti.engine.ActivitiIllegalArgumentException;
import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import com.ysusoft.complain.bean.Complain;
import com.ysusoft.complain.bean.ComplainType;
import com.ysusoft.complain.dao.ComplainDao;
import com.ysusoft.complain.dao.ComplainTypeDao;
import com.ysusoft.frame.event.bean.ExecutionBean;
import com.ysusoft.frame.event.bean.TaskBean;
import com.ysusoft.frame.event.dao.ExecutionDao;
import com.ysusoft.frame.event.dao.TaskDao;
import com.ysusoft.frame.sys.base.bean.Grid;
import com.ysusoft.frame.sys.base.bean.MediaSetting;
import com.ysusoft.frame.sys.base.bean.ResultInfo;
import com.ysusoft.frame.sys.exception.SysException;
import com.ysusoft.frame.sys.role.bean.Role;
import com.ysusoft.frame.sys.user.bean.User;
import com.ysusoft.frame.sys.user.dao.UserDao;
/**
 * 
* @ClassName: EventService 
* @Description: 业务驱动服务
* @author Wuf
* @date 2017年6月5日 
*
 */ 
@Service
public class EventService {

	@Autowired
	RepositoryService repositoryService;

	@Autowired
	RuntimeService runTimeService;

	@Autowired
	TaskService taskService;

	@Autowired
	HistoryService historyService;

	@Autowired
	FormService formService;

	@Autowired
	private TaskDao taskDao;
	
	@Autowired
	private ExecutionDao executionDao;
	
	@Autowired
	private ComplainDao complainDao;
	
	@Autowired
	private ComplainTypeDao complainTypeDao;
	
	@Autowired
	private UserDao userDao;
	

	private Sort sort = new Sort(Sort.Direction.DESC, "createDate");  
	
	private Pageable pageable;
	
	private Page<ExecutionBean> executions ;

	/**
	 * 
	* @Title: deployProcess 
	* @Description: 启动部署（工作流引擎启动）
	* @param @param name 自定义加载名称
	* @param @param fileName 设计文件名称
	* @param @return    是否成功
	* @return boolean    返回类型 
	* @throws
	 */
	public boolean deployProcess(String name,String fileName) throws SysException{
		Deployment deploy = repositoryService.createDeployment()//创建一个部署对象
				.name(name)//加名称
				.addClasspathResource(fileName+".bpmn")
				.addClasspathResource(fileName+".png").deploy();
		return (deploy.getId()!=null&&(!"".equals(deploy.getId()))?true:false);
	}

	/**
	 * 
	* @Title: startProcess 
	* @Description: 启动一个实例（一则投诉）
	* @param @param id
	* @param @return    设定文件 
	* @return boolean    返回类型 
	* @throws
	 */
	public String startProcess(String id,long roleId) throws SysException{

		Map<String, Object> map=new HashMap<String, Object>();//要在启动流程中存的流程变量map
		//TODO:动态传输
		map.put("role", "1");
		map.put("dueDate", "1");
		//流程实例相关的service
		ProcessInstance pi = runTimeService.startProcessInstanceByKey(id,map) ;//bpmn文件的ID
		return pi.getId();
	}
	
	/**
	 * 
	* @Title: startExecution 
	* @Description: 启动一个秦旅投诉案件
	* @param @param complainId 投诉单ID
	* @param @param executionType 投诉类型
	* @param @return    设定文件 
	* @return boolean    返回类型 
	* @throws
	 */
	public boolean startExecution(int complainId,String comlainType,long roleId) throws SysException{
		String appID = "qhdTourism";//秦旅项目工作流QhdTourism.bpmn的ID
		//启动一个案例实例
		String processId = this.startProcess(appID,roleId);
		//组织案例记录
		ExecutionBean executionBean = new ExecutionBean();
		Complain complain = complainDao.findBycomplainId(complainId);
		executionBean.setComplainId(complain);
		executionBean.setCreateDate(new Date());
		executionBean.setClosed("0");//0为未结案
		executionBean.setComlainType(comlainType);
		executionBean.setProcessId(processId);
		//保存案件对象
		ExecutionBean save = executionDao.save(executionBean);
		return (save.getExecutionId()==0?false:true);
	}
	
	/**
	 * 
	* @Title: getTask 
	* @Description: 获取当前用户和当前角色的任务列表
	* @param @param page 第几页
	* @param @param pageSize 每页多少行
	* @param @return    设定文件 
	* @return Grid<Object>    返回类型 
	* @throws
	 */
	public Grid<ExecutionBean> getTask(HttpSession httpSession,String backInfo ,int page,int pageSize) throws SysException{
		Grid<ExecutionBean> result  = new Grid<ExecutionBean>();
	    pageable = new PageRequest(page, pageSize, sort); 
		//获取用户信息（用户名和角色）
	    //和 com.ysusoft.frame.sys.base.utils.getUser 保持
		User user = (User)httpSession.getAttribute("currentUser");
		
		if(user!=null){//如果不等于空，则获取数据
			List<String> roleList = new ArrayList<String>();
			List<Role> roles = user.getRoles();
			for (Role role : roles) {
				roleList.add(""+role.getRoleId());
			}
			//获取当前角色所有在执行的任务
			List<Task> listPage = this.taskService.createTaskQuery()
					.taskCandidateGroupIn(roleList)
					.list();
			//案件ID集合
			List<String> pList = new ArrayList<String>();
			//任务ID集合
			List<String> tList = new ArrayList<String>();
			for (Task task : listPage) {
				pList.add(task.getProcessInstanceId());
				tList.add(task.getId());
			}
			//查询条件过滤
			if("".equals(backInfo))
			{
				executions = executionDao.findByprocessIdIn(pList, pageable);
			}
			else
			{
				List<Complain> findByBackInfoLike = complainDao.findByBackInfoLike(backInfo);
				executions = executionDao.findByprocessIdInAndComplainIdIn(pList,findByBackInfoLike, pageable);
			}
			List<ExecutionBean> content = executions.getContent();
			for (int i = 0; i < content.size(); i++) {
				ExecutionBean executionBean = content.get(i);
				for (Task task : listPage) {
					//将下个最新任务匹配到案件里
					if(task.getProcessInstanceId().equals(executionBean.getProcessId())){
						executionBean.setCurrentTask(task.getId());
						//将期限类型计算出来放到返回值中
						String taskType = task.getTaskDefinitionKey();//投诉阶段
						String complainType = executionBean.getComlainType();//投诉类型
						List<ComplainType> findByTypeAndStage = complainTypeDao.findByTypeAndStage(complainType, taskType);
						ComplainType complainTypeBean = findByTypeAndStage.get(0);
						Date createTime = task.getCreateTime();//阶段开始时间
						Date now = new Date();//当前时间
						long diff = now.getTime()-createTime.getTime();
						long hours = diff/(1000*60*60);
						long totle = complainTypeBean.getTotle();
						long danger = complainTypeBean.getDanger();
						long notice = complainTypeBean.getNotice();
						long normal = complainTypeBean.getNormal();
						if(totle-hours<=danger){//剩余时间小于危险档
							executionBean.setCueType("1");
						}else if(totle-hours<=notice){//
							executionBean.setCueType("2");
						}else if(totle-hours<=normal){//正常范围内
							executionBean.setCueType("3");
						}else{//正常范围内
							executionBean.setCueType("4");
						}
						executionBean.setStageName(task.getName());
					}
				}
				//处理多媒体的网络地址问题
				Complain complain = content.get(i).getComplainId();
				if(complain.getImages()!=null && (!"".equals(complain.getImages()))){
					//图片处理
					String img =  complain.getImages();
					String newImg = "";
					String[] split = img.split(",");
					for (String string : split) {
						string  = MediaSetting.getImg()+string;
						newImg += string+",";
					}
					complain.setImages(newImg.substring(0,newImg.length() - 1));
				}
				
				if(complain.getAudios()!=null && (!"".equals(complain.getAudios()))){
					//录音处理
					String aud = complain.getAudios();
					String newAud = "";
					String[] split2 = aud.split(",");
					for (String string : split2) {
						string  = MediaSetting.getAudio()+string;
						newAud += string+",";
					}
					complain.setAudios(newAud.substring(0,newAud.length() - 1));
				}
				
				if(complain.getVoices()!=null && (!"".equals(complain.getVoices()))){
					//影像处理
					String video = complain.getVoices();
					String newVid = "";
					String[] split3 = video.split(",");
					for (String string : split3) {
						string  = MediaSetting.getVideo()+string;
						newVid += string+",";
					}
					complain.setVoices(newVid.substring(0,newVid.length() - 1));
				}
			}
			if(executions.getSize()>0){
				result.setTotal(executions.getTotalElements());
				result.setRows(executions.getContent());
			}
		}
		return result;
	}
	
	/**
	 * 
	* @Title: getTaskForEnd 
	* @Description: 获取所有完成的任务
	* @param @param httpSession
	* @param @param backInfo
	* @param @param page
	* @param @param pageSize
	* @param @return
	* @param @throws SysException    设定文件 
	* @return Grid<ExecutionBean>    返回类型 
	* @throws
	 */
	public Grid<ExecutionBean> getTaskForEnd(HttpSession httpSession,String backInfo ,int page,int pageSize) throws SysException{
		
		Grid<ExecutionBean> result  = new Grid<ExecutionBean>();
	    pageable = new PageRequest(page, pageSize, sort); 
		//获取用户信息（用户名和角色）
	    //和 com.ysusoft.frame.sys.base.utils.getUser 保持
		User user = (User)httpSession.getAttribute("currentUser");
		
		if(user!=null){//如果不等于空，则获取数据
			Long userId = user.getUserId();
			List<TaskBean> findByUserId = taskDao.findByUserId(userId);
			//案件ID集合
			List<String> pList = new ArrayList<String>();
			for (TaskBean taskBean : findByUserId) {
				pList.add(taskBean.getExecutionId().getProcessId());
			}
			//查询条件过滤
			List<String> closeds = new ArrayList<String>(Arrays.asList("1"));//1为已结案
			if("".equals(backInfo))
			{
				executions = executionDao.findByprocessIdInAndClosedIn(pList,closeds, pageable);
			}
			else
			{
				List<Complain> findByBackInfoLike = complainDao.findByBackInfoLike(backInfo);
				executions = executionDao.findByprocessIdInAndComplainIdInAndClosedIn(pList,findByBackInfoLike,closeds, pageable);
			}
			List<ExecutionBean> content = executions.getContent();
			for (int i = 0; i < content.size(); i++) {
				//处理多媒体的网络地址问题
				Complain complain = content.get(i).getComplainId();
				if(complain.getImages()!=null && (!"".equals(complain.getImages()))){
					//图片处理
					String img =  complain.getImages();
					String newImg = "";
					String[] split = img.split(",");
					for (String string : split) {
						string  = MediaSetting.getImg()+string;
						newImg += string+",";
					}
					complain.setImages(newImg.substring(0,newImg.length() - 1));
				}
				
				if(complain.getAudios()!=null && (!"".equals(complain.getAudios()))){
					//录音处理
					String aud = complain.getAudios();
					String newAud = "";
					String[] split2 = aud.split(",");
					for (String string : split2) {
						string  = MediaSetting.getAudio()+string;
						newAud += string+",";
					}
					complain.setAudios(newAud.substring(0,newAud.length() - 1));
				}
				
				if(complain.getVoices()!=null && (!"".equals(complain.getVoices()))){
					//影像处理
					String video = complain.getVoices();
					String newVid = "";
					String[] split3 = video.split(",");
					for (String string : split3) {
						string  = MediaSetting.getVideo()+string;
						newVid += string+",";
					}
					complain.setVoices(newVid.substring(0,newVid.length() - 1));
				}
			}
			
			if(executions.getSize()>0){
				result.setTotal(executions.getTotalElements());
				result.setRows(executions.getContent());	
			}
		}
		
		return result;
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
	public ResultInfo<TaskBean> getProcess(@RequestParam("executionId")String executionId) throws SysException{
		ResultInfo<TaskBean> result = new ResultInfo<TaskBean>();
		List<TaskBean> taskes = this.taskDao.findAllByExecutionId(executionId);
		for (TaskBean taskBean : taskes) {
			User thisUser = userDao.findOne(taskBean.getUserId());
			/**
			 *  注释  by chengql 2017-9-11
				String roles = "";
				for (Role role : thisUser.getRoles()) {
					roles += role.getRoleName()+",";
				}
			**/
			taskBean.setUserName(thisUser.getUserName());//+"("+(roles.length()>0?roles.substring(0, roles.length()-1):"")+")");
			HistoricTaskInstance singleResult = historyService.createHistoricTaskInstanceQuery().taskId(taskBean.getWfTaskId()).singleResult();
			taskBean.setStage(singleResult.getName());
		}
		Map<String, List<TaskBean>> map = new HashMap<String, List<TaskBean>>();
		map.put("data", taskes);
		result.setResult(map);
		result.setSuccess(true);
		return result;
	}
	
	
	/**
	 * 
	* @Title: completeTask 
	* @Description: 通用完成工作流任务并在本系统记录
	* @param @param httpSession 用于获取session中的用户信息
	* @param @param taskBean 任务信息
	* @param @param otype 工作流下一步路径
	* @param @return
	* @param @throws SysException    设定文件 
	* @return ResultInfo<Object>    返回类型 
	* @throws
	 */
	@Transactional 
	public ResultInfo<Object> completeTask(HttpSession httpSession,TaskBean taskBean,String otype) throws SysException{
		
		ResultInfo<Object> resultInfo = new ResultInfo<Object>();//返回对象
		Map<String, Object> map=new HashMap<String, Object>();//要在启动流程中存的流程变量map
		//获取用户信息（用户名和角色）
	    //和 com.ysusoft.frame.sys.base.utils.getUser 保持
		User user = (User)httpSession.getAttribute("currentUser");
		//获取当前要完成的任务
		Task singleResult = taskService.createTaskQuery().taskId(taskBean.getWfTaskId()).singleResult();
		
		ExecutionBean backExecution = executionDao.findByprocessId(singleResult.getProcessInstanceId());
		// 如果是结束前的逻辑，则先将反馈信息给案件保存。
		if (taskBean.getFeedBack() != null
				&& (!"".equals(taskBean.getFeedBack()))) {
			backExecution.setFeedBack(taskBean.getFeedBack());
			// 将反馈信息保存后的案件。
			backExecution = executionDao.save(backExecution);
			//将该信息复制到办理反馈中。
			taskBean.setRemark(taskBean.getFeedBack());
		}
		
		//组织工作流参数
		map.put("oType", otype);
		map.put("role", taskBean.getRoleId());
		try {
			taskService.complete(taskBean.getWfTaskId(), map);
		}catch( ActivitiIllegalArgumentException ae){//此异常为非结案阶段执行结案操作。
			throw new SysException("非结案阶段，结案失败！");
		}
		catch (Exception e) {
			throw new SysException("任务完成失败！");
		}
		
		//在业务表中记录任务完成信息
		taskBean.setCreateDate(singleResult.getCreateTime());
		taskBean.setEndDate(new Date());
		taskBean.setExecutionId( backExecution );
		taskBean.setUserId(user.getUserId());
		TaskBean task = taskDao.save(taskBean);
		
		if(task.getTaskId()!=0){
			resultInfo.setSuccess(true);
			resultInfo.setMessage("完成任务成功！");
		}
		
		return resultInfo;
	}
	
	
}
