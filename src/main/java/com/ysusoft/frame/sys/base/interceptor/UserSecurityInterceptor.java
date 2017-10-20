package com.ysusoft.frame.sys.base.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.ysusoft.frame.sys.base.utils.SessionUtils;
import com.ysusoft.frame.sys.user.bean.User;

public class UserSecurityInterceptor implements HandlerInterceptor {  
  
    @Override  
    public boolean preHandle(HttpServletRequest request,  
            HttpServletResponse response, Object handler) throws Exception {  
		request.setCharacterEncoding("UTF-8");
			User loginUser = SessionUtils.getUser(request);
			if (loginUser == null) {
				response.sendRedirect("/");
				return false;
			}else
		        return true;
    }  
  
    @Override  
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}
  
    @Override  
    public void afterCompletion(HttpServletRequest request,  
            HttpServletResponse response, Object handler, Exception ex)  
            throws Exception {  
    	
    }  
    
}
