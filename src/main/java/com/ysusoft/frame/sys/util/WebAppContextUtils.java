package com.ysusoft.frame.sys.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * @author qlcheng
 * @date 2017年10月11日 上午10:29:00
 */
public class WebAppContextUtils {

	public static <T> T getDao(Class<T> clazz, HttpServletRequest request) {
		BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
		return factory.getBean(clazz);
	}
}
