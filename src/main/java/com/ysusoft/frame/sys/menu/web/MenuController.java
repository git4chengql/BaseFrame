package com.ysusoft.frame.sys.menu.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ysusoft.frame.sys.base.bean.ResultInfo;
import com.ysusoft.frame.sys.exception.SysException;
import com.ysusoft.frame.sys.menu.bean.Menu;
import com.ysusoft.frame.sys.menu.bean.Menu4Tree;
import com.ysusoft.frame.sys.menu.service.MenuService;
import com.ysusoft.frame.sys.user.bean.User;

/**
 * @author qlcheng
 * @date 2017年5月2日 下午6:17:59
 */

@Controller
@RequestMapping("menu")
public class MenuController {

	@Autowired
	private MenuService menuService;
	
	@RequestMapping("index")
	public String index(Map<String,Object> map) throws SysException{
		map.put("systems",menuService.getSystems());
		return "sys/menu";
	}
	
	/**
	 * 获取用户相应子菜单
	 * @param parentId
	 * @return
	 * @throws SysException
	 */
	@RequestMapping("getmenubyparent")
	@ResponseBody
	public List<Menu> getUserMenu(@RequestParam(name="parentId")int parentId,HttpServletRequest request) throws SysException {
		ResultInfo<Menu> menus = new ResultInfo<Menu>();
		User user = (User)request.getSession().getAttribute("currentUser");
		int userId= user.getUserId().intValue();
		menus = menuService.getMenusByUser(userId,parentId);
		List<Menu> menu =null;
		try{
			menu = menus.getResult().get("menus");
		}catch(Exception e){
			e.getStackTrace();
		}
		return menu;
	}
	
	/**
	 * 获取用户相应权限菜单
	 * @param userId
	 * @param id
	 * @return
	 * @throws SysException
	 */
	@RequestMapping("getallusermenu")
	@ResponseBody
	public List<Menu4Tree> getUserAllMenu(@RequestParam(name="userId")int userId,@RequestParam(name="id",defaultValue="-1")int id) throws SysException{
			List<Menu4Tree> menus = menuService.getAllUserMenus(userId,id).getResult().get("menus");
			return menus;
	}
	
	/**
	 * lpf
	 * 获取角色相应的菜单
	 * @param roleId
	 * @param id
	 * @return
	 * @throws SysException
	 */
	@RequestMapping("getallrolemenu")
	@ResponseBody
	public List<Menu4Tree> getRoleAllMenu(@RequestParam(name="roleId")int roleId,@RequestParam(name="id",defaultValue="-1")int id) throws SysException{
			List<Menu4Tree> menus = menuService.getAllRoleMenus(roleId,id).getResult().get("menus");
			return menus;
	}
	
	
	@RequestMapping("getallmenu")
	@ResponseBody
	public List<Menu4Tree> getAllMenu(@RequestParam(name="id",defaultValue="-1")int menuId) throws SysException{
			List<Menu4Tree> menus = menuService.getMenuByParentId(menuId).getResult().get("menus");
			return menus;
	}
	
	/**
	 * 新增菜单
	 * @param menu
	 * @return
	 */
	@RequestMapping("addmenu")
	@ResponseBody
	public ResultInfo<Menu> addMenu(Menu menu) throws SysException{
		return menuService.addMenu(menu);
	}
	
	/**
	 * 编辑菜单
	 * @param menu
	 * @return
	 */
	@RequestMapping("updatemenu")
	@ResponseBody
	public ResultInfo<Menu> updateMenu(int menuId,int systemId,int parentMenuId,int level,int isFlag,String icon,String title,String href) throws SysException{
		return menuService.updateMenu(menuId,systemId,parentMenuId,level,isFlag,icon,title,href);
	}
	
	/**
	 * 删除菜单
	 */
	@RequestMapping("delmenu")
	@ResponseBody
	public ResultInfo<Menu> delMenu(int menuId,int level) throws SysException{
		return menuService.deleteMenu(menuId,level);
	}
	
	/**
	 * 根据菜单id查找菜单
	 * @param menuId
	 * @return
	 */
	@RequestMapping("getmenubyId")
	@ResponseBody
	public Menu getInfobyId(int id) throws SysException{
		return menuService.getMenuBymenuId(id);
	}
}
