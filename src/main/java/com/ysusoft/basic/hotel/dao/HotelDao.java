package com.ysusoft.basic.hotel.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ysusoft.basic.hotel.bean.Hotel;


/**
 *  酒店
 * @author songyy
 * @date 2017年6月2日 下午1:57:03
 */

public interface HotelDao extends JpaRepository<Hotel, Long> {

	/**
	 * 根据名称分页查询
	 * @param name
	 * @param pageable
	 * @return
	 */
	Page<Hotel> findByHotelName(String name,Pageable pageable); 
	
	/**
	 * 分页查询所有
	 * @param pageable
	 * @return
	 */
	Page<Hotel> findByHotelNameNotNull(Pageable pageable);
	
	/**
	 * 根据标示删除
	 * @param hotelId
	 */
	public int deleteByHotelId(Integer hotelId);
	
	/**
	 * 查询总数
	 * @return
	 */
	 @Query(value="select count(*) from  t_base_hotel",nativeQuery=true)
	public int getAllSum();
}
