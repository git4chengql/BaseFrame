package com.ysusoft.basic.faq.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ysusoft.basic.faq.bean.FAQBean;

/**
 * 
* @ClassName: FAQDao 
* @Description: 常见问题的DAO
* @author Wuf
* @date 2017年8月11日 
*
 */
public interface FAQDao  extends JpaRepository<FAQBean, Integer>{
	

	public Page<FAQBean> findBytype(String type,Pageable pageable);
	
	public FAQBean findByfaqId(Integer faqId);
}
