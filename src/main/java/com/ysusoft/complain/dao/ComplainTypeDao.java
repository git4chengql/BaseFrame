package com.ysusoft.complain.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.ysusoft.complain.bean.ComplainType;

public interface ComplainTypeDao  extends JpaRepository<ComplainType, Integer>{
	

	 @Query(value="select a.danger,a.normal,a.notice,a.stage,a.totle,a.type, "+
			" count(b.comlainType) as complainTypeId from (select *from t_complain_type "+
			"		 GROUP BY type) a"+
			"		 left join t_event_execution b on a.type=b.comlainType"+
			"		 GROUP BY a.type",nativeQuery=true)
	public  List<ComplainType> getComplainTypeCount();

	public List<ComplainType> findByTypeAndStage(String type,String stage);
}
