package com.ysusoft.basic.hotel.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ysusoft.basic.hotel.bean.Hotel;
import com.ysusoft.basic.hotel.dao.HotelDao;
import com.ysusoft.frame.sys.base.bean.Grid;
import com.ysusoft.frame.sys.base.bean.ResultInfo;
import com.ysusoft.frame.sys.exception.SysException;

/**
 * 酒店
 * @author songyy
 * @date 2017年6月2日 下午1:57:03
 */

@Service
public class HotelService {

	@Autowired
	private HotelDao hotelDao;
	
	private Sort sort = new Sort(Sort.Direction.ASC, "hotelId");  
	
	private Pageable pageable;
	
	private Page<Hotel> pages;
	
	private ResultInfo<Hotel> rs = new ResultInfo<Hotel>();
	
	
	/**
	 * 查询所有数据/根据名称查询
	 * @return 
	 */
	public Grid<Hotel> getAllHotel(String hotelName,int page,int pageSize) throws SysException{
	    pageable = new PageRequest(page, pageSize, sort); 
	    if(hotelName!="")
		      pages = hotelDao.findByHotelName(hotelName,pageable); 
		else
		      pages= hotelDao.findByHotelNameNotNull(pageable);
	    Grid<Hotel> hotel = new Grid<Hotel>();
		if(pages.getSize()>0){
			hotel.setRows(pages.getContent());
			hotel.setTotal(pages.getTotalElements());
		}
		return hotel;
	}
    
	/**
	 * 新增
	 */
	public ResultInfo<Hotel> addHotel(Hotel hotel) throws SysException{
		Hotel r = hotelDao.save(hotel);
		if(r!=null){
			rs.setSuccess(true);
			rs.setMessage("保存成功");
		}else{
			rs.setMessage("保存失败");
		}
		return rs;
	}
	
	/**
	 * 编辑
	 */
	@Transactional
	public ResultInfo<Hotel> updateHotel(Hotel hotel) throws SysException{
		Hotel r = hotelDao.save(hotel);
		if(r!=null){
			rs.setSuccess(true);
			rs.setMessage("更新成功");
		}else{
			rs.setMessage("更新失败");
		}
		return rs;
	}
	
	/**
	 * 根据标识删除
	 * @param hotelId 标识
	 */
	@Transactional
	public ResultInfo<Hotel> deleteHotel(Integer hotelId) throws SysException{
		if(hotelDao.deleteByHotelId(hotelId)>0){
			rs.setSuccess(true);
			rs.setMessage("删除成功");
		}else{
			rs.setMessage("删除失败");
		}
		return rs;
	}
}
