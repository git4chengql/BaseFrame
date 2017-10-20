package com.ysusoft.basic.shopping.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ysusoft.basic.shopping.bean.Shopping;
import com.ysusoft.basic.shopping.service.ShoppingService;
import com.ysusoft.frame.sys.base.bean.Grid;
import com.ysusoft.frame.sys.base.bean.ResultInfo;
import com.ysusoft.frame.sys.exception.SysException;

/**
 * 购物点
 * @author songyy
 * 修改lpf 0609
 * @date 2017年6月2日 下午3:57:03
 */

@Controller
@RequestMapping("shopping/")
public class ShoppingController {

	@Autowired
	private ShoppingService shoppingService;
	
	/**
	 * 索引页
	 * @return
	 */
	@RequestMapping("index")
	public String index(){
		return "basicdata/shopping";
	}
	
	/**
	 * 分页查询所有数据
	 * @param page 页码
	 * @param pageSize 大小
	 * @return
	 */
	@RequestMapping("allshopping")
	@ResponseBody
	public Grid<Shopping> getAllSpot(@RequestParam("page")int page,@RequestParam("pageSize")int pageSize,@RequestParam("shoppingName")String shoppingName) throws SysException{
		return shoppingService.getAllShopping(shoppingName,page-1,pageSize);
	}
	
	/**
	 * 新增、编辑
	 * @param shopping
	 * @return
	 */
	@RequestMapping("addorupdateshopping")
	@ResponseBody
	public ResultInfo<Shopping> addShopping(Shopping shopping) throws SysException{
		if(shopping.getShoppingId()==null){
			return shoppingService.addShopping(shopping);
		}else{
			return shoppingService.updateShopping(shopping);
		}
	}
	
	/**
	 * lpf删除一条或者多条记录
	 * @param request
	 * @return
	 * @throws SysException
	 */
	@RequestMapping("delshopping")
	@ResponseBody
	public ResultInfo<Shopping> deleteShopping(HttpServletRequest request) throws SysException{
		ResultInfo<Shopping> rs=new ResultInfo<Shopping>();
		String fids = ServletRequestUtils.getStringParameter(request, "shoppingIds","");
		String fidList[] = null;
		if (fids.indexOf(",") == -1) {
			fidList = new String[1];
			fidList[0] = fids;
		} else {
			fidList = fids.split(",");
		}
		int ids[] = new int[fidList.length];
		int index = 0;
		for (String id : fidList) {
			ids[index] = Integer.parseInt(id);
			index++;
		}
		for (int id : ids) {
			rs= shoppingService.deleteShopping(id);
		}
		return rs;
	}
	
}