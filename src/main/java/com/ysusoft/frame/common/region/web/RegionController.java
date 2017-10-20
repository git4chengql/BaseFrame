package com.ysusoft.frame.common.region.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ysusoft.frame.common.region.service.RegionService;
import com.ysusoft.frame.sys.exception.SysException;
import com.ysusoft.frame.sys.menu.bean.Menu4Tree;

/**
 * @author songyy
 * @date 2017年6月5日 区划树
 */

@Controller
@RequestMapping("region")
public class RegionController {

	@Autowired
	private RegionService regionService;
	
	/**
	 * 获取区划树-全国
	 */
	@RequestMapping("getregiontree")
	@ResponseBody
	public List<Menu4Tree> getRegionTree(HttpServletRequest request) throws SysException{
		int id = ServletRequestUtils.getIntParameter(request, "id", -1);
		List<Menu4Tree> tree = regionService.getRegionTree(id);
		return tree;
    }
	
	
}
