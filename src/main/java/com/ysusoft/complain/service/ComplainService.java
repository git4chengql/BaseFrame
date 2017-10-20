package com.ysusoft.complain.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ysusoft.complain.bean.Complain;
import com.ysusoft.complain.dao.ComplainDao;
import com.ysusoft.frame.event.service.EventService;
import com.ysusoft.frame.sys.base.bean.MediaSetting;
import com.ysusoft.frame.sys.base.bean.ResultInfo;
import com.ysusoft.frame.sys.exception.SysException;

/**
 * 投诉
 * @author songyy
 * @date 2017年6月6日 下午6:57:03
 */

@Service
public class ComplainService {

	@Autowired
	private ComplainDao complainDao;
	
	private ResultInfo<Complain> rs = new ResultInfo<Complain>();

	@Autowired
	private EventService eventService;
	
	/**
	 * 新增
	 */
	public ResultInfo<Complain> addComplain(Complain complain) throws SysException{
		String timeString = new SimpleDateFormat("yyyyMMddHHmmssSSS") .format(new Date());
		complain.setBackInfo(timeString);
		complain.setCreateDate(new Date());
		Complain r = complainDao.save(complain);
		//启动一个工作流案件
		boolean startExecution = eventService.startExecution(r.getComplainId(), complain.getComplainType(), Long.parseLong(complain.getUserId()));//TODO: 暂时用userid传输roleid
		if(r!=null && startExecution){
			rs.setSuccess(true);
			rs.setMessage(timeString);
		}else{
			
			rs.setMessage("提交失败");
		}
		return rs;
	}
	
	/**
	 * 
	* @Title: addComplainForWX 
	* @Description: 微信调用新增投诉接口
	* @param @param complain
	* @param @param userid
	* @param @return
	* @param @throws SysException    设定文件 
	* @return ResultInfo<Complain>    返回类型 
	* @throws
	 */
	public ResultInfo<Complain> addComplainForWX(Complain complain,String userid) throws SysException{
		String timeString = new SimpleDateFormat("yyyyMMddHHmmssSSS") .format(new Date());
		complain.setBackInfo(timeString);
		complain.setCreateDate(new Date());
		Complain r = complainDao.save(complain);
		//启动一个工作流案件
		boolean startExecution = eventService.startExecution(r.getComplainId(), complain.getComplainType(), Long.parseLong(userid));//TODO: 暂时用userid传输roleid
		if(r!=null && startExecution){
			rs.setSuccess(true);
			rs.setMessage(timeString);
		}else{
			
			rs.setMessage("提交失败");
		}
		return rs;
	}
	

	public ResultInfo<Complain> getComplain(String userId,String type,HttpServletRequest request) throws SysException{
		rs.setMessage("查询接口调用成功！");
		rs.setSuccess(true);
		List<Complain> findByUserId = this.complainDao.findByUserIdOrderByCreateDateDesc(userId);
		for (Complain complain : findByUserId) {
			//微信端说需要将null转为“”
			if(complain.getResultMsg()==null&&"wx".equals(type))
				complain.setResultMsg("");
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
//					string  = MediaSetting.getAudio()+string;
					//TODO: 等待解决方案。临时写死一个
					string  = MediaSetting.getAudio()+"123.mp3";
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
		Map<String,List<Complain>> a = new HashMap<String,List<Complain>>();
		a.put("complain", findByUserId);
		rs.setResult(a);
		return rs;
	}
	
}
