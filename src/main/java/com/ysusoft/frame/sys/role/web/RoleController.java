package com.ysusoft.frame.sys.role.web;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ysusoft.frame.sys.base.bean.Grid;
import com.ysusoft.frame.sys.base.bean.ResultInfo;
import com.ysusoft.frame.sys.exception.SysException;
import com.ysusoft.frame.sys.role.bean.Role;
import com.ysusoft.frame.sys.role.bean.RoleMenu;
import com.ysusoft.frame.sys.role.service.RoleService;

/**
 * 角色管理
 * @author qlcheng
 * @date 2017年4月11日 下午1:57:03
 */

@Controller
@RequestMapping("role/")
public class RoleController {

	@Autowired
	private RoleService rs;
	
	/**
	 * 角色索引页
	 * @return
	 */
	@RequestMapping("index")
	public String index(){
		return "sys/role";
	}
	
	/**
	 * 新增、更新角色
	 * @param role
	 * @return
	 */
	@RequestMapping("addrole")
	@ResponseBody
	public ResultInfo<Role> addRole(Role role){
		if(role.getRoleId()==0l)
		    return  rs.addRole(role);
		else
			return rs.updateRole(role);
	}
	
	
	/**
	 * 分页查询所有角色数据
	 * @param page 页码
	 * @param pageSize 大小
	 * @return
	 */
	@RequestMapping("allrole")
	@ResponseBody
	public Grid<Role> getAllRole(@RequestParam("page")int page,@RequestParam("pageSize")int pageSize,@RequestParam("roleName")String roleName){
		return rs.getAllRole(roleName,page-1,pageSize);
	}
	
	
	/**
	 * @param request
	 * @return
	 * @throws SysException
	 */
	@RequestMapping("deleteRoleIds")
	@ResponseBody
	public ResultInfo<Role> deleteRoleIds(@RequestParam(value = "roleIdslist") String roleIdslist[]) throws SysException{
		return rs.deleteRole(roleIdslist);
	}
	
	/**
	 * 保存角色的菜单权限
	 * @param request
	 * @return
	 */
	@RequestMapping("saveRoleMenu")
	@ResponseBody
	public ResultInfo<RoleMenu> saveRoleMenu(@RequestParam(value = "menus") String menus[],@RequestParam("roleId")int roleId) throws Exception{
		return rs.addRoleMenu(menus, roleId);
	}

	/**
	 * 
	* @Title: getAllRoles 
	* @Description: 获取所有角色（严谨的说是获取所有ID不为0的角色）
	* @param @return    设定文件 
	* @return ResultInfo<Role>    返回类型 
	* @throws
	 */
	@RequestMapping("getAllRoles")
	@ResponseBody
	public ResultInfo<Role> getAllRoles() throws SysException{
		ResultInfo<Role> result = new ResultInfo<Role>();
		List<Long> empty = new ArrayList<Long>(Arrays.asList(0L));
		List<Role> roles = rs.getAllRoleNotInRoleId(empty);
		Map<String, List<Role>> map = new HashMap<String, List<Role>>();
		map.put("data", roles);
		result.setSuccess(true);
		result.setResult(map);
		return result;
	}
}