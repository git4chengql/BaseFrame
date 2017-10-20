package com.ysusoft.basic.guides.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ysusoft.basic.guides.bean.Guides;
import com.ysusoft.basic.guides.service.GuidesService;
import com.ysusoft.frame.sys.base.bean.Grid;
import com.ysusoft.frame.sys.base.bean.ResultInfo;
import com.ysusoft.frame.sys.exception.SysException;

/**
 * 导游
 * @author songyy
 * @date 2017年6月2日 上午9:37:03
 */

@Controller
@RequestMapping("guides/")
public class GuidesController {

	@Autowired
	private GuidesService gs;
	
	/**
	 * 索引页
	 * @return
	 */
	@RequestMapping("index")
	public String index(Map<String,Object> map) throws SysException{
		map.put("travels",gs.getTravels());
		return "basicdata/guides";
	}
	
	/**
	 * 分页查询所有数据
	 * @param page 页码
	 * @param pageSize 大小
	 * @return
	 */
	@RequestMapping("allguides")
	@ResponseBody
	public Grid<Guides> getAllGuides(@RequestParam("page")int page,@RequestParam("pageSize")int pageSize,@RequestParam("guideName")String guideName) throws SysException{
		return gs.getAllGuides(guideName,page-1,pageSize);
	}
	
	/**
	 * 根据id查找
	 */
	@RequestMapping("getguidebyId")
	@ResponseBody
	public List<Guides> getGuideById(@RequestParam("guideId")Integer guideId) throws SysException{
		return gs.getGuideById(guideId);
	}
	
	
	/**
	 * 新增、编辑
	 * @param guide
	 * @return
	 */
	@RequestMapping("addorupdateguide")
	@ResponseBody
	public ResultInfo<Guides> addGuide(Guides guide) throws SysException{
		if(guide.getGuideId()==null){
			return gs.addGuide(guide);
		}else{
			return gs.updateGuide(guide);
		}
	}
	
	/**
	 * 删除
	 * @param guide
	 * @return
	 */
	@RequestMapping("delguide")
	@ResponseBody
	public ResultInfo<Guides> deleteGuide(Integer guideId) throws SysException{
		return gs.deleteGuide(guideId);
	}
	
}