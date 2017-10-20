package com.ysusoft.basic.faq.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ysusoft.basic.faq.bean.FAQBean;
import com.ysusoft.basic.faq.dao.FAQDao;
import com.ysusoft.frame.sys.base.bean.Grid;
import com.ysusoft.frame.sys.base.bean.ResultInfo;
import com.ysusoft.frame.sys.exception.SysException;

/**
 * 
* @ClassName: FAQService 
* @Description: 常见问题服务
* @author Wuf
* @date 2017年8月11日 
*
 */
@Service
public class FAQService {

	@Autowired
	private FAQDao faqDao;
	

	private Sort sort = new Sort(Sort.Direction.DESC, "faqId");  
	
	private Pageable pageable;

	public Grid<FAQBean> getFAQ(String type,int page,int pageSize) throws SysException{

		Grid<FAQBean> result  = new Grid<FAQBean>();
	    pageable = new PageRequest(page, pageSize, sort); 
		
	    Page<FAQBean> findBytype = faqDao.findBytype(type, pageable);
	    
	    if(findBytype.getSize()>0){
			result.setTotal(findBytype.getTotalElements());
			result.setRows(findBytype.getContent());	
		}
		return result;
	}
	
	public ResultInfo<String> getFAQContent(int faqId) throws SysException{
		
		ResultInfo<String> rs = new ResultInfo<String>();
		FAQBean findOne = faqDao.findByfaqId(faqId);
		
		rs.setSuccess(true);
		rs.setMessage(findOne.getContent());
		return rs;
	}
	
}
