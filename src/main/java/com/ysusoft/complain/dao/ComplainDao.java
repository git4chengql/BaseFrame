package com.ysusoft.complain.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ysusoft.complain.bean.Complain;

/**
 * 投诉
 * 
 * @author songyy
 * @date 2017年6月6日 下午6:57:03
 */

public interface ComplainDao extends JpaRepository<Complain, Long> {

	public Complain findBycomplainId(Integer ComplainId);

	public List<Complain> findByBackInfoLike(String backInfo);

	public List<Complain> findByUserIdOrderByCreateDateDesc(String userId);
}
