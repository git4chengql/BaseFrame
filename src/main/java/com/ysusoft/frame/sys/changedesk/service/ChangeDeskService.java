package com.ysusoft.frame.sys.changedesk.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ysusoft.basic.guides.dao.GuidesDao;
import com.ysusoft.basic.hotel.dao.HotelDao;
import com.ysusoft.basic.shopping.dao.ShoppingDao;
import com.ysusoft.basic.spot.dao.SpotDao;
import com.ysusoft.basic.travel.dao.TravelDao;
import com.ysusoft.complain.bean.ComplainType;
import com.ysusoft.complain.dao.ComplainTypeDao;
import com.ysusoft.frame.sys.changedesk.bean.ChangeDeskBean;
import com.ysusoft.frame.sys.changedesk.bean.ComplainCount;
import com.ysusoft.frame.sys.changedesk.dao.ChangeDeskDao;

@Service
public class ChangeDeskService {
 @Autowired
 private TravelDao  travelDao;
 @Autowired
 private GuidesDao  guidesDao;
 @Autowired
 private HotelDao  hotelDao;
 @Autowired
 private SpotDao  spotDao;
 @Autowired
 private ShoppingDao  shoppingDao;
 @Autowired
 private ChangeDeskDao  deskDao;
 @Autowired
 private ComplainTypeDao  typeDao;
 
 public ChangeDeskBean getAllSum(){
	 ChangeDeskBean deskBean=new ChangeDeskBean();
	 deskBean.setTravel(travelDao.getTravelSum());
	 deskBean.setGuides(guidesDao.getAllSum());
	 deskBean.setHotel(hotelDao.getAllSum());
	 deskBean.setShopping(shoppingDao.getAllSum());
	 deskBean.setSpot(spotDao.getAllSum());
	 return deskBean;
 }
 

 public List<ComplainCount> getComplainCounts() {
	return deskDao.getComplainCount();
}
 
 public  List<ComplainType> getComplainTypeCounts() {
		return typeDao.getComplainTypeCount();
 }
 
 
 
 
	
}
