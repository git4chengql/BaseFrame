package com.ysusoft.frame.sys.base.bean;

import java.util.List;

/**
 * @author qlcheng
 * @date 2017年4月13日 下午4:50:18
 */
public class BeanMsg {

	private String summary;
	private List<FieldMsg> fields;

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public void setFields(List<FieldMsg> fields) {
		this.fields = fields;
	}

	public List<FieldMsg> getFields() {
		return fields;
	}

}
