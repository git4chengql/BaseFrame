package com.ysusoft.frame.sys.changedesk.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ysusoft.frame.sys.changedesk.bean.ComplainCount;

public interface ChangeDeskDao extends JpaRepository<ComplainCount,Integer>{
	
	
	/**
	 * 按月份的到投诉的统计数目
	 * @return
	 */
   @Query(value="select a.complaindate,count(b.createDate) as comcount,a.id from t_complaindate  a "+
			" left join t_event_execution b on a.complaindate= DATE_FORMAT(b.createDate,'%m') "+
			" group by a.complaindate",nativeQuery=true)
	public  List<ComplainCount> getComplainCount();

}
