package com.ysusoft.frame.sys.menu.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ysusoft.frame.sys.base.bean.ResultInfo;
import com.ysusoft.frame.sys.exception.SysException;
import com.ysusoft.frame.sys.menu.bean.AllSystem;
import com.ysusoft.frame.sys.menu.bean.Menu;
import com.ysusoft.frame.sys.menu.bean.Menu4Tree;
import com.ysusoft.frame.sys.menu.dao.MenuDao;
import com.ysusoft.frame.sys.menu.dao.SystemDao;

/**
 * @author qlcheng
 * @date 2017年5月2日 下午3:58:08
 */

@Service
public class MenuService {

	@Autowired
	private MenuDao menuDao;
	@Autowired
	private SystemDao systemDao;
	
	private ResultInfo<Menu> resultInfo;
	
	private Map<String,List<Menu>> menus = new HashMap<String,List<Menu>>();
	
	/**
	 * 获取用户顶部菜单
	 * @param userId
	 * @return
	 */
	public ResultInfo<Menu> getBigMenusByUser(int userId,int systemId) throws Exception{
		   //查询一级菜单
		    List<Menu> menu = menuDao.findByLevelAndUser(0,userId,-1,systemId);//level,user,parentid
			resultInfo = new ResultInfo<Menu>();
			menus.put("menus",menu);
			resultInfo.setResult(menus);
			resultInfo.setSuccess(true);
			resultInfo.setMessage("查询成功");
			return resultInfo;
	}
	
	/**
	 * 获取用户左侧菜单
	 * @param userId
	 * @return
	 */
	public ResultInfo<Menu> getMenusByUser(int userId,int parentId) throws SysException{
		//查询一级菜单
		Menu menu = menuDao.findByMenuIdOrderByOrderNumAsc(parentId);
		menu.setSpread(true);
		//查询二级菜单
		List<Menu> secondMenus = menuDao.findByLevelAndUserForChild(1,userId,parentId);
		//组装结构
		if(secondMenus.size()>0)
		     menu.setChildren(secondMenus);
		else
			 menu.setChildren(new ArrayList<Menu>());
		
		List<Menu> userMenu = new ArrayList<Menu>();
		userMenu.add(menu);
		resultInfo = new ResultInfo<Menu>();
		menus.put("menus",userMenu);
		resultInfo.setResult(menus);
		resultInfo.setSuccess(true);
		resultInfo.setMessage("查询成功");
		
		return resultInfo;
	}
	
	
	/**
	 * 查第一个一级菜单下的第一个子菜单
	 * @param userId
	 * @param systemId
	 * @return
	 * @throws Exception
	 */
	public List<Menu> getFirstMenusByUser(int userId,int systemId) throws Exception{
		    List<Menu> menu = menuDao.getFirstMenusByUser(userId,systemId);
			return menu;
	}
	
	/**
	 * 查带地图的系统的菜单
	 */
	public Menu getMapByUser(int systemId) throws Exception{
		Menu menu = menuDao.getMapByUser(systemId);
		return menu;
	}

	
	/**
	 * 查询菜单及用户权限
	 * @param userId
	 * @param menuId
	 * @return
	 */
	public ResultInfo<Menu4Tree> getAllUserMenus(int userId,int menuId) throws SysException{
		List<Menu> menu = menuDao.findAllUserMenus(userId,menuId);
		List<Menu4Tree> treeNodes = new ArrayList<Menu4Tree>();
		for(Menu m : menu){
			Menu4Tree mt = new Menu4Tree();
			mt.setId(m.getMenuId());
			mt.setText(m.getTitle());
			mt.setChecked(m.getChecked());
			mt.setState(m.getState());
			mt.setLevel(m.getLevel());
			mt.setpId(m.getParentMenuId());
			treeNodes.add(mt);
		}
		ResultInfo<Menu4Tree> resultInfo = new ResultInfo<Menu4Tree>();
	    Map<String,List<Menu4Tree>> treemenu = new HashMap<String,List<Menu4Tree>>();
	  
		treemenu.put("menus",treeNodes);
		resultInfo.setResult(treemenu);
		resultInfo.setSuccess(true);
		resultInfo.setMessage("查询成功");
		return resultInfo;
	}
	
	/**
	 * lpf
	 * 查询角色菜单权限
	 * @param roleId
	 * @param meunId
	 * @return
	 * @throws SysException
	 */
   public ResultInfo<Menu4Tree> getAllRoleMenus(int roleId,int menuId)throws SysException{
	   List<Menu> menu = menuDao.findAllRoleMenus(roleId,menuId);
		List<Menu4Tree> treeNodes = new ArrayList<Menu4Tree>();
		for(Menu m : menu){
			Menu4Tree mt = new Menu4Tree();
			mt.setId(m.getMenuId());
			mt.setText(m.getTitle());
			mt.setChecked(m.getChecked());
			mt.setState(m.getState());
			mt.setLevel(m.getLevel());
			mt.setpId(m.getParentMenuId());
			treeNodes.add(mt);
		}
		ResultInfo<Menu4Tree> resultInfo = new ResultInfo<Menu4Tree>();
	    Map<String,List<Menu4Tree>> treemenu = new HashMap<String,List<Menu4Tree>>();
		treemenu.put("menus",treeNodes);
		resultInfo.setResult(treemenu);
		resultInfo.setSuccess(true);
		resultInfo.setMessage("查询成功");
		return resultInfo;
	}
	/**
	 * 获取菜单
	 * @return
	 */
	public ResultInfo<Menu4Tree> getMenuByParentId(int menuId){
		List<Menu4Tree> treeNodes = new ArrayList<Menu4Tree>();
		List<Menu> menus = menuDao.findByParentMenuId(menuId);
		for(Menu m : menus){
			Menu4Tree mt = new Menu4Tree();
			mt.setId(m.getMenuId());
			mt.setText(m.getTitle());
			mt.setChecked(m.getChecked());
			mt.setState(m.getState());
			mt.setLevel(m.getLevel());
			mt.setpId(m.getParentMenuId());
			mt.setSystemId(m.getSystemId());
			treeNodes.add(mt);
		}
		ResultInfo<Menu4Tree> resultInfo = new ResultInfo<Menu4Tree>();
	    Map<String,List<Menu4Tree>> treemenu = new HashMap<String,List<Menu4Tree>>();
	  
		treemenu.put("menus",treeNodes);
		resultInfo.setResult(treemenu);
		resultInfo.setSuccess(true);
		resultInfo.setMessage("查询成功");
		return resultInfo;
	}
	
	/**
	 * 新增菜单
	 */
	public ResultInfo<Menu> addMenu(Menu menu) throws SysException{
		Menu r = menuDao.save(menu);
		if(r!=null){
			resultInfo.setSuccess(true);
			resultInfo.setMessage("保存成功");
		}else{
			resultInfo.setMessage("保存失败");
		}
		return resultInfo;
	}
	
	/**
	 * 编辑菜单
	 */
	@Transactional
	public ResultInfo<Menu> updateMenu(int menuId,int systemId,int parentMenuId,int level,int isFlag,String icon,String title,String href) throws SysException{
		int r = menuDao.updateMenu(menuId, systemId, parentMenuId, level, isFlag, icon, title, href);
		if(r==1){
			resultInfo.setSuccess(true);
			resultInfo.setMessage("更新成功");
		}else{
			resultInfo.setMessage("更新失败");
		}
		return resultInfo;
	}
	
	/**
	 * 根据菜单标识删除菜单
	 * @param menuId 菜单标识
	 */
	@Transactional
	public ResultInfo<Menu> deleteMenu(int menuId,int level) throws SysException{
		if(level==1){
			if(menuDao.deleteByMenuId(menuId)>0){
				resultInfo.setSuccess(true);
				resultInfo.setMessage("删除成功");
			}else{
				resultInfo.setMessage("删除失败");
			}
		}else if(level==0){
			int parentMenuId = menuId;
			if(menuDao.deleteByMenuId(menuId)>0){
				if(this.getMenuBymenuId(menuId)!=null){//是否有二级菜单
					if(menuDao.deleteByParentMenuId(parentMenuId)>0){
						resultInfo.setSuccess(true);
						resultInfo.setMessage("删除成功");
					}else{
						resultInfo.setMessage("删除失败");
					}
				}else{
					resultInfo.setSuccess(true);
					resultInfo.setMessage("删除成功");
				}
			}else{
				resultInfo.setMessage("删除失败");
			}
		}
		return resultInfo;
	}
	
	/**
	 * 根据菜单id查询菜单
	 * @param id 菜单表主键
	 * @return
	 */
	public Menu getMenuBymenuId(int id) throws SysException{
		return menuDao.findByMenuIdOrderByOrderNumAsc(id);
	}
	
	/**
	 * 返回所有系统名称
	 * @return
	 * @throws SysException
	 */
	public List<AllSystem> getSystems()  throws SysException{
		List<AllSystem> system = systemDao.findBysystemNameNotNull();
		return system;
	}
}
