package com.ysusoft.basic.spot.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ysusoft.basic.spot.bean.Spot;


/**
 *  酒店
 * @author songyy
 * @date 2017年6月2日 下午1:57:03
 */

public interface SpotDao extends JpaRepository<Spot, Long> {

	/**
	 * 根据名称分页查询
	 * @param name
	 * @param pageable
	 * @return
	 */
	Page<Spot> findBySpotName(String name,Pageable pageable); 
	
	/**
	 * 分页查询所有
	 * @param pageable
	 * @return
	 */
	Page<Spot> findBySpotNameNotNull(Pageable pageable);
	
	/**
	 * 根据标示删除
	 * @param hotelId
	 */
	public int deleteBySpotId(Integer spotId);
	
	/**
	 * 查询总数
	 * @return
	 */
	 @Query(value="select count(*) from  t_base_spot",nativeQuery=true)
	 public int getAllSum();
}
