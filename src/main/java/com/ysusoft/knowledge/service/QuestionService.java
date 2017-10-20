package com.ysusoft.knowledge.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ysusoft.frame.sys.exception.SysException;
import com.ysusoft.knowledge.bean.Question;
import com.ysusoft.knowledge.dao.QuestionDao;

/**
 * 知识库-常见问题
 * @author songyy
 * @date 2017年8月15日
 */

@Service
public class QuestionService {
	
	@Autowired
	private QuestionDao qDao;

	public List<Question> getQuestion()throws SysException{
		List<Question> list = qDao.findByKnowTitleNotNull();
		return list;
	}
	
}
