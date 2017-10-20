package com.ysusoft.frame.sys.base.bean;

import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

/**
 * @author qlcheng
 * @date 2017年4月11日 下午1:57:03
 * JSON格式返回固定格式
 */
@Component
@ConfigurationProperties(prefix = "ysusoft")
public class ResultInfo<T> {

    //是否成功
	private boolean isSuccess = false;
	//返回提示信息
	private String message ;
	//返回数据
	private Map<String,List<T>> result;
	
	private Page<T> pageData;

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Map<String, List<T>> getResult() {
		return result;
	}

	public void setResult(Map<String, List<T>> result) {
		this.result = result;
	}

	public Page<T> getPageData() {
		return pageData;
	}

	public void setPageData(Page<T> pageData) {
		this.pageData = pageData;
	}
	
	

}
