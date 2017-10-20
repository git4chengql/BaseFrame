package com.ysusoft.basic.shopping.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ysusoft.basic.shopping.bean.Shopping;

/**
 *  购物点
 * @author songyy
 * @date 2017年6月2日 下午1:57:03
 */

public interface ShoppingDao extends JpaRepository<Shopping, Long> {

	/**
	 * 根据名称分页查询
	 * @param name
	 * @param pageable
	 * @return
	 */
	
	/*@Query(value=" from  Shopping   s where s.ShoppingName like %?1%  ")*/
	Page<Shopping> findByShoppingNameLike(String ShoppingName,Pageable pageable); 
	
	/**
	 * 分页查询所有
	 * @param pageable
	 * @return
	 */
	Page<Shopping> findByShoppingNameNotNull(Pageable pageable);
	
	/**
	 * 根据标示删除
	 * @param hotelId
	 */
	public int deleteByShoppingId(Integer shoppingId);
	
	/**
	 * 查询总数
	 * @return
	 */
	 @Query(value="select count(*) from  t_base_shopping",nativeQuery=true)
	public int  getAllSum();
}
