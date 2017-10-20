package com.ysusoft.basic.hotel.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ysusoft.basic.hotel.bean.Hotel;
import com.ysusoft.basic.hotel.service.HotelService;
import com.ysusoft.frame.sys.base.bean.Grid;
import com.ysusoft.frame.sys.base.bean.ResultInfo;
import com.ysusoft.frame.sys.exception.SysException;

/**
 * 酒店
 * @author songyy
 * @date 2017年6月2日 下午1:57:03
 */

@Controller
@RequestMapping("hotel/")
public class HotelController {

	@Autowired
	private HotelService hs;
	
	/**
	 * 索引页
	 * @return
	 */
	@RequestMapping("index")
	public String index(){
		return "basicdata/hotel";
	}
	
	/**
	 * 分页查询所有数据
	 * @param page 页码
	 * @param pageSize 大小
	 * @return
	 */
	@RequestMapping("allhotel")
	@ResponseBody
	public Grid<Hotel> getAllHotel(@RequestParam("page")int page,@RequestParam("pageSize")int pageSize,@RequestParam("hotelName")String hotelName) throws SysException{
		return hs.getAllHotel(hotelName,page-1,pageSize);
	}
	
	/**
	 * 新增、编辑
	 * @param hotelId
	 * @return
	 */
	@RequestMapping("addorupdatehotel")
	@ResponseBody
	public ResultInfo<Hotel> addTravel(Hotel hotel) throws SysException{
		if(hotel.getHotelId()==null){
			return hs.addHotel(hotel);
		}else{
			return hs.updateHotel(hotel);
		}
	}
	
	/**
	 * 删除
	 * @param hotelId
	 * @return
	 */
	@RequestMapping("delhotel")
	@ResponseBody
	public ResultInfo<Hotel> deleteHotel(Integer hotelId) throws SysException{
		return hs.deleteHotel(hotelId);
	}
	
}