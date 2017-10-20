package com.ysusoft.basic.travel.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ysusoft.basic.travel.bean.Travel;


/**
 *  旅行社
 * @author songyy
 * @date 2017年6月1日 下午1:57:03
 */

public interface TravelDao extends JpaRepository<Travel, Long> {

	/**
	 * 根据名称分页查询
	 * @param name
	 * @param pageable
	 * @return
	 */
	Page<Travel> findByTravelNameLike(String name,Pageable pageable); 
	
	/**
	 * 分页查询所有
	 * @param pageable
	 * @return
	 */
	Page<Travel> findByTravelNameNotNull(Pageable pageable);
	
	List<Travel> findByTravelNameNotNull();
	
	/**
	 * 根据标示删除
	 * @param travelId
	 */
	public int deleteByTravelId(Integer travelId);
	
	
	/**
	 * 查询总数
	 * @return
	 */
	 @Query(value="select count(*) from t_base_travel",nativeQuery=true)
	 public int getTravelSum();
}
