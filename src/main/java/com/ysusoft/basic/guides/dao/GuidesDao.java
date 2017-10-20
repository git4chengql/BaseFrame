package com.ysusoft.basic.guides.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ysusoft.basic.guides.bean.Guides;


/**
 *  导游
 * @author songyy
 * @date 2017年6月1日 下午1:57:03
 */

public interface GuidesDao extends JpaRepository<Guides, Long> {

	/**
	 * 根据名称分页查询
	 * @param name
	 * @param pageable
	 * @return
	 */
	Page<Guides> findByGuideNameLike(String name,Pageable pageable); 
	
	/**
	 * 分页查询所有
	 * @param pageable
	 * @return
	 */
	Page<Guides> findByGuideNameNotNull(Pageable pageable);
	
	/**
	 * 根据id查询
	 */
	List<Guides> findByGuideId(Integer guideId);
	
	/**
	 * 根据标示删除
	 * @param travelId
	 */
	public int deleteByGuideId(Integer guideId);
	
	/**
	 * 查询总数
	 * @return
	 */
	 @Query(value="select count(*) from  t_base_guides",nativeQuery=true)
	public int getAllSum();
}
