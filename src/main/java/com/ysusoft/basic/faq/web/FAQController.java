package com.ysusoft.basic.faq.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ysusoft.basic.faq.service.FAQService;


/**
 * 
* @ClassName: FAQController 
* @Description: 常见问题控制层
* @author Wuf
* @date 2017年8月11日 
*
 */
@Controller
@RequestMapping("faq/")
public class FAQController {

	@SuppressWarnings("unused")
	@Autowired
	private FAQService fs;
	
	
}
