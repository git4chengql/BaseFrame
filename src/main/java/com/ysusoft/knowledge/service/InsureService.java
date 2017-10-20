package com.ysusoft.knowledge.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ysusoft.frame.sys.exception.SysException;
import com.ysusoft.knowledge.bean.Insure;
import com.ysusoft.knowledge.dao.InsureDao;

/**
 * 知识库-保险知识
 * @author songyy
 * @date 2017年8月16日
 */

@Service
public class InsureService {
	
	@Autowired
	private InsureDao insureDao;

	public List<Insure> getInsure()throws SysException{
		List<Insure> list = insureDao.findByInsureTitleNotNull();
		return list;
	}
	
}
