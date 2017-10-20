package com.ysusoft.knowledge.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ysusoft.frame.sys.exception.SysException;
import com.ysusoft.knowledge.bean.Law;
import com.ysusoft.knowledge.dao.LawDao;

/**
 * 知识库-法律法规
 * @author songyy
 * @date 2017年8月15日
 */

@Service
public class LawService {
	
	@Autowired
	private LawDao lawDao;

	public List<Law> getLaw()throws SysException{
		List<Law> list = lawDao.findByLawTitleNotNull();
		return list;
	}
	
}
