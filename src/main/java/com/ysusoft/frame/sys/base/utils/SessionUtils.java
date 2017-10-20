package com.ysusoft.frame.sys.base.utils;


import javax.servlet.http.HttpServletRequest;

import com.ysusoft.frame.sys.user.bean.User;

/**
 * session 管理
 * @author chengql
 *
 */
public class SessionUtils {
	public static User getUser(HttpServletRequest request){
		try{
			User user = (User)request.getSession().getAttribute("currentUser");
			return user;
		}catch(Exception e){
			return null;
		}
	}
	
	public static void setSession(HttpServletRequest request,User user){
		request.getSession().setAttribute("currentUser", user);
	}
	
	public static void removeSession(HttpServletRequest request){
		request.getSession().removeAttribute("currentUser");
	}
	
}

