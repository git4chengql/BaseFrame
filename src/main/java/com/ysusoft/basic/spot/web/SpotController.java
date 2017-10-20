package com.ysusoft.basic.spot.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ysusoft.basic.spot.bean.Spot;
import com.ysusoft.basic.spot.service.SpotService;
import com.ysusoft.frame.sys.base.bean.Grid;
import com.ysusoft.frame.sys.base.bean.ResultInfo;
import com.ysusoft.frame.sys.exception.SysException;

/**
 * 旅游景区
 * @author songyy
 * @date 2017年6月2日 下午3:57:03
 */

@Controller
@RequestMapping("spot/")
public class SpotController {

	@Autowired
	private SpotService spotService;
	
	/**
	 * 索引页
	 * @return
	 */
	@RequestMapping("index")
	public String index(){
		return "basicdata/spot";
	}
	
	/**
	 * 分页查询所有数据
	 * @param page 页码
	 * @param pageSize 大小
	 * @return
	 */
	@RequestMapping("allspot")
	@ResponseBody
	public Grid<Spot> getAllSpot(@RequestParam("page")int page,@RequestParam("pageSize")int pageSize,@RequestParam("spotName")String spotName) throws SysException{
		return spotService.getAllSpot(spotName,page-1,pageSize);
	}
	
	/**
	 * 新增、编辑
	 * @param spotId
	 * @return
	 */
	@RequestMapping("addorupdatespot")
	@ResponseBody
	public ResultInfo<Spot> addSpot(Spot Spot) throws SysException{
		if(Spot.getSpotId()==null){
			return spotService.addSpot(Spot);
		}else{
			return spotService.updateSpot(Spot);
		}
	}
	
	/**
	 * 删除
	 * @param spotId
	 * @return
	 */
	@RequestMapping("delspot")
	@ResponseBody
	public ResultInfo<Spot> deleteSpot(Integer spotId) throws SysException{
		return spotService.deleteSpot(spotId);
	}
	
}