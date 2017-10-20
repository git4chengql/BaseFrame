package com.ysusoft.frame.common.nation.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ysusoft.frame.common.nation.bean.Nation;
import com.ysusoft.frame.common.nation.service.NationService;
import com.ysusoft.frame.sys.exception.SysException;

/**
 * @author songyy
 * @date 2017年6月14日 
 */

@Controller
@RequestMapping("nation")
public class NationController {

	@Autowired
	private NationService nationService;
	
	/**
	 * 基础信息列表
	 */
	@RequestMapping("getnation")
	@ResponseBody
	public List<Nation> getNation() throws SysException{
		return nationService.getNation();
    }
	
	
}
