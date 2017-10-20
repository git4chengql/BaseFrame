package com.ysusoft.frame.sys.base.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.ysusoft.frame.sys.base.bean.YsuLog;
import com.ysusoft.frame.sys.base.web.LogDao;
import com.ysusoft.frame.sys.util.LogUtil;
import com.ysusoft.frame.sys.util.WebAppContextUtils;

/**
 * @author qlcheng
 * @date 2017年10月11日 上午9:32:22 日志拦截器 1.记录系统所有请求的日志
 */
public class LogInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest req, HttpServletResponse resp, Object object, Exception exception)
			throws Exception {
		
	}

	@Override
	public void postHandle(HttpServletRequest req, HttpServletResponse resp, Object object, ModelAndView mv)
			throws Exception {
	}

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object object) throws Exception {
		YsuLog log = LogUtil.getLog(req);
		LogDao logDao = WebAppContextUtils.getDao(LogDao.class,req);
		logDao.save(log);
		return true;
	}

}
