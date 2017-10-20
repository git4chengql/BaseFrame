package com.ysusoft.frame.common.edu.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ysusoft.frame.common.edu.bean.Edu;
import com.ysusoft.frame.common.edu.dao.EduDao;
import com.ysusoft.frame.sys.exception.SysException;

/**
 * @author songyy
 * @date 2017年6月5日 下午3:58:08
 */

@Service
public class EduService {

	@Autowired
	private EduDao eduDao;
	
	
	/**
	 * 基础信息
	 */
	public List<Edu> getEdu() throws SysException{
		List<Edu> list = eduDao.findByEduNameNotNull();
		return list;
	}
	
}
