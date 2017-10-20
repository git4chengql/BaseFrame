package com.ysusoft.knowledge.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ysusoft.frame.sys.exception.SysException;
import com.ysusoft.knowledge.bean.Solution;
import com.ysusoft.knowledge.dao.SolutionDao;

/**
 * 知识库-解决措施
 * @author songyy
 * @date 2017年8月16日
 */

@Service
public class SolutionService {
	
	@Autowired
	private SolutionDao solutDao;

	public List<Solution> getSolution()throws SysException{
		List<Solution> list = solutDao.findBySolutTitleNotNull();
		return list;
	}
	
}
