package com.ysusoft.frame.sys.user.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ysusoft.frame.sys.user.bean.User;

/**
 * @author qlcheng
 * @date 2017年4月10日 下午3:21:59
 */
public interface UserDao extends JpaRepository<User, Long> {

	/**
	 * 根据标示删除
	 * 
	 * @param userId
	 */
	public int deleteByUserId(long userId);

	/**
	 * 根据名称分页查询
	 * @param name
	 * @param pageable
	 * @return
	 */
	Page<User> findByUserNameLike(String name,Pageable pageable); 
	
	/**
	 * 分页查询所有
	 * @param pageable
	 * @return
	 */
	Page<User> findByUserNameNotNull(Pageable pageable);
	
	/**
	 * 登录
	 * @param loginName
	 * @param password
	 * @return
	 */
	public  List<User> findByLoginNameAndPassword(String loginName,String password);
	
}
