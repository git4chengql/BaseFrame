package com.ysusoft.frame.common.edu.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ysusoft.frame.common.edu.bean.Edu;
import com.ysusoft.frame.common.edu.service.EduService;
import com.ysusoft.frame.sys.exception.SysException;

/**
 * @author songyy
 * @date 2017年6月14日 
 */

@Controller
@RequestMapping("edu")
public class EduController {

	@Autowired
	private EduService eduService;
	
	/**
	 * 基础信息列表
	 */
	@RequestMapping("getedu")
	@ResponseBody
	public List<Edu> getBaseData() throws SysException{
		return eduService.getEdu();
    }
	
	
}
