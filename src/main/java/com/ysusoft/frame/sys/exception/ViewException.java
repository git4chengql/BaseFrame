package com.ysusoft.frame.sys.exception;

/**
 * 试图相关异常
 * @author qlcheng
 * @date 2017年5月26日 下午2:45:24
 */
public class ViewException extends Exception {

	private static final long serialVersionUID = 1L;

	public ViewException(){
		
	}
	
	public ViewException(String msg){
		super(msg);
	}
}
