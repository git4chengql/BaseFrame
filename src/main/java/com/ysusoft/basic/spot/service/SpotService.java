package com.ysusoft.basic.spot.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ysusoft.basic.spot.bean.Spot;
import com.ysusoft.basic.spot.dao.SpotDao;
import com.ysusoft.frame.sys.base.bean.Grid;
import com.ysusoft.frame.sys.base.bean.ResultInfo;
import com.ysusoft.frame.sys.exception.SysException;

/**
 * 酒店
 * @author songyy
 * @date 2017年6月2日 下午1:57:03
 */

@Service
public class SpotService {

	@Autowired
	private SpotDao spotDao;
	
	private Sort sort = new Sort(Sort.Direction.ASC, "spotId");  
	
	private Pageable pageable;
	
	private Page<Spot> pages;
	
	private ResultInfo<Spot> rs = new ResultInfo<Spot>();
	
	
	/**
	 * 查询所有数据/根据名称查询
	 * @return 
	 */
	public Grid<Spot> getAllSpot(String spotName,int page,int pageSize) throws SysException{
	    pageable = new PageRequest(page, pageSize, sort); 
	    if(spotName!="")
		      pages = spotDao.findBySpotName(spotName,pageable); 
		else
		      pages= spotDao.findBySpotNameNotNull(pageable);
	    Grid<Spot> spot = new Grid<Spot>();
		if(pages.getSize()>0){
			spot.setRows(pages.getContent());
			spot.setTotal(pages.getTotalElements());
		}
		return spot;
	}
    
	/**
	 * 新增
	 */
	public ResultInfo<Spot> addSpot(Spot spot) throws SysException{
		Spot r = spotDao.save(spot);
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
	public ResultInfo<Spot> updateSpot(Spot spot) throws SysException{
		Spot r = spotDao.save(spot);
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
	public ResultInfo<Spot> deleteSpot(Integer spotId) throws SysException{
		if(spotDao.deleteBySpotId(spotId)>0){
			rs.setSuccess(true);
			rs.setMessage("删除成功");
		}else{
			rs.setMessage("删除失败");
		}
		return rs;
	}
}
