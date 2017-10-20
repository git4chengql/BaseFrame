package com.ysusoft.basic.guides.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ysusoft.basic.guides.bean.Guides;
import com.ysusoft.basic.guides.dao.GuidesDao;
import com.ysusoft.basic.travel.bean.Travel;
import com.ysusoft.basic.travel.dao.TravelDao;
import com.ysusoft.frame.sys.base.bean.Grid;
import com.ysusoft.frame.sys.base.bean.ResultInfo;
import com.ysusoft.frame.sys.exception.SysException;

/**
 * 导游
 * @author songyy
 * @date 2017年6月1日 下午1:57:03
 */

@Service
public class GuidesService {

	@Autowired
	private GuidesDao guidesDao;
	
	@Autowired
	private TravelDao travelDao;
	
	private Sort sort = new Sort(Sort.Direction.ASC, "guideId");  
	
	private Pageable pageable;
	
	private Page<Guides> pages;
	
	private ResultInfo<Guides> rs = new ResultInfo<Guides>();
	
	/**
	 * 查询所有数据/根据名称查询
	 * @return 
	 */
	public Grid<Guides> getAllGuides(String guideName,int page,int pageSize) throws SysException{
	    pageable = new PageRequest(page, pageSize, sort); 
	    if(guideName!="")
		      pages = guidesDao.findByGuideNameLike("%"+guideName+"%",pageable); 
		else
		      pages= guidesDao.findByGuideNameNotNull(pageable);
	    Grid<Guides> guides = new Grid<Guides>();
		if(pages.getSize()>0){
			guides.setRows(pages.getContent());
			guides.setTotal(pages.getTotalElements());
		}
		return guides;
	}
	
	
	public List<Guides> getGuideById(Integer guideId)throws SysException{
		List<Guides> list = guidesDao.findByGuideId(guideId);
		return list;
	}
	/**
	 * 新增
	 */
	public ResultInfo<Guides> addGuide(Guides guide) throws SysException{
		Guides r = guidesDao.save(guide);
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
	public ResultInfo<Guides> updateGuide(Guides guide) throws SysException{
		Guides r = guidesDao.save(guide);
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
	public ResultInfo<Guides> deleteGuide(Integer guideId) throws SysException{
		if(guidesDao.deleteByGuideId(guideId)>0){
			rs.setSuccess(true);
			rs.setMessage("删除成功");
		}else{
			rs.setMessage("删除失败");
		}
		return rs;
	}
	
	/**
	 * 查询所有旅行社数据
	 */
	public List<Travel> getTravels()  throws SysException{
		List<Travel> travel = travelDao.findByTravelNameNotNull();
		return travel;
	}
	
}
