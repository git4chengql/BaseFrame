package com.ysusoft.basic.travel.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ysusoft.basic.travel.bean.Travel;
import com.ysusoft.basic.travel.dao.TravelDao;
import com.ysusoft.frame.sys.base.bean.Grid;
import com.ysusoft.frame.sys.base.bean.ResultInfo;
import com.ysusoft.frame.sys.exception.SysException;

/**
 * 旅行社
 * @author songyy
 * @date 2017年6月1日 下午1:57:03
 */

@Service
public class TravelService {

	@Autowired
	private TravelDao travelDao;
	
	private Sort sort = new Sort(Sort.Direction.ASC, "travelId");  
	
	private Pageable pageable;
	
	private Page<Travel> pages;
	
	private ResultInfo<Travel> rs = new ResultInfo<Travel>();
	
	
	/**
	 * 查询所有数据/根据名称查询
	 * @return 
	 */
	public Grid<Travel> getAllTravel(String travelName,int page,int pageSize) throws SysException{
	    pageable = new PageRequest(page, pageSize, sort); 
	    if(travelName!="")
		      pages = travelDao.findByTravelNameLike("%"+travelName+"%",pageable); 
		else
		      pages= travelDao.findByTravelNameNotNull(pageable);
	    Grid<Travel> travel = new Grid<Travel>();
		if(pages.getSize()>0){
			travel.setRows(pages.getContent());
			travel.setTotal(pages.getTotalElements());
		}
		return travel;
	}
    
	/**
	 * 新增
	 */
	public ResultInfo<Travel> addTravel(Travel travel) throws SysException{
		Travel r = travelDao.save(travel);
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
	public ResultInfo<Travel> updateTravel(Travel travel) throws SysException{
		Travel r = travelDao.save(travel);
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
	 * @param travelId 标识
	 */
	@Transactional
	public ResultInfo<Travel> deleteTravel(Integer travelId) throws SysException{
		if(travelDao.deleteByTravelId(travelId)>0){
			rs.setSuccess(true);
			rs.setMessage("删除成功");
		}else{
			rs.setMessage("删除失败");
		}
		return rs;
	}
}
