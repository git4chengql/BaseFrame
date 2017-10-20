package com.ysusoft.basic.travel.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ysusoft.basic.travel.bean.Travel;
import com.ysusoft.basic.travel.service.TravelService;
import com.ysusoft.frame.sys.base.bean.Grid;
import com.ysusoft.frame.sys.base.bean.ResultInfo;
import com.ysusoft.frame.sys.exception.SysException;

/**
 * 旅行社
 * @author songyy
 * @date 2017年6月1日 下午1:57:03
 */

@Controller
@RequestMapping("travel/")
public class TravelController {

	@Autowired
	private TravelService ts;
	
	/**
	 * 索引页
	 * @return
	 */
	@RequestMapping("index")
	public String index(){
		return "basicdata/travel";
	}
	
	/**
	 * 分页查询所有数据
	 * @param page 页码
	 * @param pageSize 大小
	 * @return
	 */
	@RequestMapping("alltravel")
	@ResponseBody
	public Grid<Travel> getAllTravel(@RequestParam("page")int page,@RequestParam("pageSize")int pageSize,@RequestParam("travelName")String travelName) throws SysException{
		return ts.getAllTravel(travelName,page-1,pageSize);
	}
	
	/**
	 * 新增、编辑
	 * @param travel
	 * @return
	 */
	@RequestMapping("addorupdatetravel")
	@ResponseBody
	public ResultInfo<Travel> addTravel(Travel travel) throws SysException{
		if(travel.getTravelId()==null){
			return ts.addTravel(travel);
		}else{
			return ts.updateTravel(travel);
		}
	}
	
	/**
	 * 删除
	 * @param travelId
	 * @return
	 */
	@RequestMapping("deltravel")
	@ResponseBody
	public ResultInfo<Travel> deleteTravel(Integer travelId) {
		ResultInfo<Travel> rs = new ResultInfo<Travel>();
		try{
			rs = ts.deleteTravel(travelId);
		}catch(Exception e){
			rs.setMessage("删除异常");
			e.printStackTrace();
		}
		return rs;
	}
	
}