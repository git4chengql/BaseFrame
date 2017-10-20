package com.ysusoft.frame.common.region.service;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ysusoft.frame.common.region.bean.Region;
import com.ysusoft.frame.common.region.dao.RegionDao;
import com.ysusoft.frame.sys.exception.SysException;
import com.ysusoft.frame.sys.menu.bean.Menu4Tree;

/**
 * @author songyy
 * @date 2017年6月5日 下午3:58:08
 */

@Service
public class RegionService {

	@Autowired
	private RegionDao regionDao;
	
	
	/**
	 * 区划树
	 */
	public List<Menu4Tree> getRegionTree(int id) throws SysException{
		List<Region> tree = regionDao.getRegionTree(id);
		List<Menu4Tree> treeNodes = new ArrayList<Menu4Tree>();
		for(Region r : tree){
			Menu4Tree mt = new Menu4Tree();
			mt.setId(r.getFid());
			mt.setText(r.getAreaname());
			mt.setpId(r.getParentid());
			mt.setLevel(r.getFlevel());
			mt.setState(r.getState());
			treeNodes.add(mt);
		}
		return treeNodes;
	}
	
}
