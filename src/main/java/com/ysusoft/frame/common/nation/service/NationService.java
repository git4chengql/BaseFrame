package com.ysusoft.frame.common.nation.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ysusoft.frame.common.nation.bean.Nation;
import com.ysusoft.frame.common.nation.dao.NationDao;
import com.ysusoft.frame.sys.exception.SysException;

/**
 * @author songyy
 * @date 2017年6月5日 下午3:58:08
 */

@Service
public class NationService {

	@Autowired
	private NationDao nationDao;
	
	
	/**
	 * 基础信息
	 */
	public List<Nation> getNation() throws SysException{
		List<Nation> list = nationDao.findByNationNameNotNull();
		return list;
	}
	
}
