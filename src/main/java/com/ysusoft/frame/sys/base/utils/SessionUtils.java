package com.ysusoft.frame.sys.base.utils;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.ysusoft.frame.sys.base.session.ISession;
import com.ysusoft.frame.sys.base.session.impl.ISessionWithRedisImpl;
import com.ysusoft.frame.sys.user.bean.User;
import org.springframework.stereotype.Service;

/**
 * session 管理
 * @author chengql
 *
 */
@Service
public class SessionUtils {
	@Resource(type = ISessionWithRedisImpl.class)
	private ISession<User> iSession;

	public static User getUser(HttpServletRequest request){
		try{
			User user = (User)request.getSession().getAttribute("currentUser");
			return user;
		}catch(Exception e){
			return null;
		}
	}
	
	public  void setSession(User user){
		iSession.addSession(user);
	}
	
	public static void removeSession(HttpServletRequest request){
		request.getSession().removeAttribute("currentUser");
	}
	
}

