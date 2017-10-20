package com.ysusoft.knowledge.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ysusoft.frame.sys.exception.SysException;
import com.ysusoft.knowledge.bean.Behavior;
import com.ysusoft.knowledge.dao.BehaviorDao;

/**
 * 知识库-法律法规
 * @author songyy
 * @date 2017年8月15日
 */

@Service
public class BehaviorService {
	
	@Autowired
	private BehaviorDao behvDao;

	public List<Behavior> getBehv()throws SysException{
		List<Behavior> list = behvDao.findByBehvTitleNotNull();
		return list;
	}
	
}
