package com.ysusoft.frame.sys.base.bean;

/**
 * 属性Bean
 * @author qlcheng
 * @date 2017年4月12日 下午5:53:55
 */
public class FieldMsg {

	private String fieldName;
	private Class<?> fieldType;
	private String fieldSummary;
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public Class<?> getFieldType() {
		return fieldType;
	}
	public void setFieldType(Class<?> fieldType) {
		this.fieldType = fieldType;
	}
	public String getFieldSummary() {
		return fieldSummary;
	}
	public void setFieldSummary(String fieldSummary) {
		this.fieldSummary = fieldSummary;
	}
	
	
}
