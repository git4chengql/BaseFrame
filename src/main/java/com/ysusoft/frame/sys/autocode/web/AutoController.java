package com.ysusoft.frame.sys.autocode.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ysusoft.frame.sys.autocode.service.CodeService;
import com.ysusoft.frame.sys.base.bean.BeanMsg;

/**
 * @author qlcheng
 * @date 2017年4月12日 下午10:45:13
 * 代码生成器
 */
@Controller
@RequestMapping("autocode/")
public class AutoController {

	@Autowired
	private CodeService cs;
	
    @GetMapping("index")
	public String index(){
		return "sys/autocode";
	}
    
    @RequestMapping("getbean")
    @ResponseBody
    public BeanMsg getBeanMsg(String beanName){
      	return cs.getEntityMsg(beanName);
    }
}
