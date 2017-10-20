package com.ysusoft.frame.sys.base.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ysusoft.frame.sys.base.bean.SystemSetting;
import com.ysusoft.frame.sys.base.utils.SessionUtils;
import com.ysusoft.frame.sys.exception.SysException;
import com.ysusoft.frame.sys.menu.service.MenuService;
import com.ysusoft.frame.sys.user.bean.User;

/**
 * @author qlcheng
 * @date 2017年4月12日 下午3:37:57
 */
@Controller
public class DeskController {

	@Autowired
	private MenuService menuService;
	
	@Autowired
	private SystemSetting systemSetting;
	
	
	@RequestMapping("/")
	public String login(Map<String,Object> map){
		map.put("systemsetting",systemSetting);
		return "sys/login";
	}
	
	/**
	 * 系统列表页
	 */
	@RequestMapping("/syslist")
	public String syslist(Map<String,Object> map,HttpServletRequest request) throws SysException {
		return "sys/syslist";
	}
	
	/**
	 * 首页索引
	 * @return
	 * @throws SysException 
	 */
	@RequestMapping("/desk")
	public String index(Map<String,Object> map,@RequestParam(name="systemId")int systemId,@RequestParam(name="systemName")String systemName,HttpServletRequest request) throws SysException {
		try {
			map.put("systemsetting",systemSetting);
			map.put("systemName",systemName);
			User user = (User)request.getSession().getAttribute("currentUser");
			int userId= user.getUserId().intValue();
			map.put("menus", menuService.getBigMenusByUser(userId,systemId).getResult().get("menus"));
			if(systemId!=6){
				map.put("systemId", 0);
				map.put("firstmenu", menuService.getFirstMenusByUser(userId,systemId).get(0));
			}else{
				map.put("systemId", 6);
				map.put("firstmenu", menuService.getMapByUser(systemId));
			}
			map.put("currentUser", request.getSession().getAttribute("currentUser"));
		} catch (Exception e) {
			throw new SysException(e.getMessage());
		}
		return "sys/desk";
	}
	
	
	@RequestMapping("changedesk")
	public String index(){
		return "sys/changedesk";
	}
	
	
	
	/**
	 * 注销
	 * @param request
	 * @return
	 */
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request){
		SessionUtils.removeSession(request);
		return "/";
	}
}
