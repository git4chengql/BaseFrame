package com.ysusoft.basic.shopping.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ysusoft.basic.shopping.bean.Shopping;
import com.ysusoft.basic.shopping.dao.ShoppingDao;
import com.ysusoft.frame.sys.base.bean.Grid;
import com.ysusoft.frame.sys.base.bean.ResultInfo;
import com.ysusoft.frame.sys.exception.SysException;

/**
 * 购物点
 * @author songyy
 * @date 2017年6月2日 下午1:57:03
 */

@Service
public class ShoppingService {

	@Autowired
	private ShoppingDao shoppingDao;
	
	private Sort sort = new Sort(Sort.Direction.ASC, "shoppingId");  
	
	private Pageable pageable;
	
	private Page<Shopping> pages;
	
	private ResultInfo<Shopping> rs = new ResultInfo<Shopping>();
	
	
	/**
	 * 查询所有数据/根据名称查询
	 * @return 
	 * 李鹏飞修改模糊查询
	 */
	public Grid<Shopping> getAllShopping(String shoppingName,int page,int pageSize) throws SysException{
	    pageable = new PageRequest(page, pageSize, sort); 
	    if(shoppingName!="")
		      pages = shoppingDao.findByShoppingNameLike("%"+shoppingName+"%",pageable); 
		else
		      pages= shoppingDao.findByShoppingNameNotNull(pageable);
	    Grid<Shopping> shopping = new Grid<Shopping>();
		if(pages.getSize()>0){
			shopping.setRows(pages.getContent());
			shopping.setTotal(pages.getTotalElements());
		}
		return shopping;
	}
    
	/**
	 * 新增
	 */
	public ResultInfo<Shopping> addShopping(Shopping shopping) throws SysException{
		Shopping r = shoppingDao.save(shopping);
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
	public ResultInfo<Shopping> updateShopping(Shopping shopping) throws SysException{
		Shopping r = shoppingDao.save(shopping);
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
	 * @param shoppingId 标识
	 */
	@Transactional
	public ResultInfo<Shopping> deleteShopping(Integer shoppingId) throws SysException{
		if(shoppingDao.deleteByShoppingId(shoppingId)>0){
			rs.setSuccess(true);
			rs.setMessage("删除成功");
		}else{
			rs.setMessage("删除失败");
		}
		return rs;
	}
}
