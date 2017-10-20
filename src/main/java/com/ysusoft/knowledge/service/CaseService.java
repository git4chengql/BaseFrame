package com.ysusoft.knowledge.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ysusoft.frame.sys.exception.SysException;
import com.ysusoft.knowledge.bean.Case;
import com.ysusoft.knowledge.dao.CaseDao;

/**
 * 知识库-经典案例
 * @author songyy
 * @date 2017年8月16日
 */

@Service
public class CaseService {
	
	@Autowired
	private CaseDao cseDao;

	public List<Case> getCase()throws SysException{
		List<Case> list = cseDao.findByCaseTitleNotNull();
		return list;
	}
	
}
