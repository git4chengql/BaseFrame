package com.ysusoft.knowledge.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ysusoft.frame.sys.exception.SysException;
import com.ysusoft.knowledge.bean.Standard;
import com.ysusoft.knowledge.dao.StandardDao;

/**
 * 知识库-行业标准
 * @author songyy
 * @date 2017年8月15日
 */

@Service
public class StandardService {
	
	@Autowired
	private StandardDao standDao;

	public List<Standard> getStandard()throws SysException{
		List<Standard> list = standDao.findByStandTitleNotNull();
		return list;
	}
	
}
