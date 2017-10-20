package com.ysusoft.frame.common.region.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ysusoft.frame.common.region.bean.Region;



/**
 * @author songyy
 * @date 2017年6月5日 下午2:41:03
 */
public interface RegionDao extends JpaRepository<Region,Long> {
	
	/**
	 * 得到区划树
	 */
	@Query(value=" SELECT m.fid,m.areaname,m.parentid,m.flevel, "+
       "   (CASE WHEN (SELECT COUNT(1) FROM t_base_area d1 WHERE d1.parentid=m.fid)>0 THEN 'closed' ELSE 'open' END) AS state"+
       "      FROM t_base_area m"+
       "      WHERE parentid=?1 ",nativeQuery=true)
	public List<Region> getRegionTree(int id);
	

}
