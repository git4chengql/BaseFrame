package com.ysusoft.frame.sys.exception;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ysusoft.frame.sys.base.bean.ResultInfo;

/**
 * @desc 全局异常处理类
 * @author qlcheng
 * @date 2017年5月26日 下午1:24:42
 */
@ControllerAdvice(basePackages={"com.ysusoft"})
public class GlobalExceptionHandler {
 
	   /**
	    * 系统异常类处理
	    * @param e
	    * @param response
	    * @return
	    */
	   @ExceptionHandler(SysException.class)
	   @ResponseBody
	   public ResultInfo<String> exceptionHandler(SysException e, HttpServletResponse response){
		   //日志处理
		   ResultInfo<String> rs = new ResultInfo<String>();
		   rs.setMessage(e.getMessage());
		   return rs;
	   }
	   
}
