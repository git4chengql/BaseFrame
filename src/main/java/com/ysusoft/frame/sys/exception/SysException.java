package com.ysusoft.frame.sys.exception;

/**
 * 系统异常类
 * @author qlcheng
 * @date 2017年5月26日 下午1:33:16
 */
public class SysException  extends  Exception{

	private static final long serialVersionUID = 1L;

	public SysException(){
		
	}
	
	public SysException(String exceptionMsg){
		super(exceptionMsg);
	} 
}
