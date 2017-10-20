package com.ysusoft.frame.sys.changedesk.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ysusoft.complain.bean.ComplainType;
import com.ysusoft.frame.sys.changedesk.bean.ChangeDeskBean;
import com.ysusoft.frame.sys.changedesk.bean.ComplainCount;
import com.ysusoft.frame.sys.changedesk.service.ChangeDeskService;

@Controller
@RequestMapping("changedesk/")
public class ChangeDeskController {

	@Autowired
	private ChangeDeskService deskService;
	
	
	/**
	 * 得到五个种类的数目
	 * @return
	 */
	@RequestMapping("getAllSum")
	@ResponseBody
	public Map<Object,Object> getAllSum(){
		Map<Object,Object> map=new HashMap<Object,Object>();
		try {
			ChangeDeskBean changeDeskBean=	deskService.getAllSum();
			map.put("desk", changeDeskBean);
		} catch (Exception e) {
			
		}
		return  map;
	}
	
	@RequestMapping("getComplainSum")
	@ResponseBody
	public Map<Object,Object> getComplainSum(){
		Map<Object,Object> map=new HashMap<Object,Object>();
		try {
			List<ComplainCount> counts=deskService.getComplainCounts();
			List<ComplainType> types=deskService.getComplainTypeCounts();
			map.put("counts", counts);
			map.put("types", types);
		} catch (Exception e) {
		}
		return  map;
	}
	
}
