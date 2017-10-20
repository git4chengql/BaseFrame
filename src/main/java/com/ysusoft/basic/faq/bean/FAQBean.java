package com.ysusoft.basic.faq.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
* @ClassName: FAQBean 
* @Description: 常见问题实体
* @author Wuf
* @date 2017年8月11日 
*
 */
@Entity
@Table(name="t_Base_FAQ")
public class FAQBean {

	@Id
	@GeneratedValue
	private Integer faqId;
	
	/**
	 * 问题标题
	 */
	private String title;
	
	/**
	 * 解答内容
	 */
	private String content;
	
	/**
	 * 问题分类 如:服务质量：1，卫生状况：2，欺客宰客：3，门票价格：4，其他：5,等.
	 */
	private String type;

	public Integer getFaqId() {
		return faqId;
	}

	public void setFaqId(Integer faqId) {
		this.faqId = faqId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}