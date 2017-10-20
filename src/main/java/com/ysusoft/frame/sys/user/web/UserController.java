package com.ysusoft.frame.sys.user.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ysusoft.frame.sys.base.bean.Grid;
import com.ysusoft.frame.sys.base.bean.ResultInfo;
import com.ysusoft.frame.sys.base.utils.SessionUtils;
import com.ysusoft.frame.sys.exception.SysException;
import com.ysusoft.frame.sys.role.bean.Role;
import com.ysusoft.frame.sys.role.service.RoleService;
import com.ysusoft.frame.sys.user.bean.User;
import com.ysusoft.frame.sys.user.bean.UserMenu;
import com.ysusoft.frame.sys.user.service.UserService;

/**
 * @author qlcheng
 * @date 2017年4月10日 下午3:24:21
 */
@Controller
@RequestMapping("user/")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;

	/**
	 * 
	* @Title: index 
	* @Description: 用户索引页
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	@RequestMapping("index")
	public String index(){
		return "sys/user";
	}

	/**
	 * 
	* @Title: getUserForAddOrUpdate 
	* @Description: 新增或者更新用户时，通过此方法获取页面数据
	* @param @param modelMap
	* @param @param userId 更新用户的ID
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	@RequestMapping("getUserForAddOrUpdate")
	public String getUserForAddOrUpdate(ModelMap modelMap,@RequestParam(defaultValue = "0") long userId){
		if(userId==0L){//新增
			List<Long> empty = new ArrayList<Long>(Arrays.asList(0L));
			List<Role> roles = this.roleService.getAllRoleNotInRoleId(empty);
			modelMap.addAttribute("role", roles);
		}else {//更新
			User user = this.userService.getUserByUserId(userId);
			if(user!=null){
				user.setPassword("");
				modelMap.addAttribute("user", user);
				List<Role> userRole = user.getRoles();
				modelMap.addAttribute("userRole", userRole);
				List<Long> ids = new ArrayList<Long>(Arrays.asList(0L));
				for (Role role : userRole) {
					ids.add(role.getRoleId());
				}
				StringBuffer txtGroupsSelect = new StringBuffer();
				for (Role role : userRole) {
					txtGroupsSelect.append(role.getRoleId());
					txtGroupsSelect.append(",");
				}
				modelMap.addAttribute("txtGroupsSelect", ("".equals(txtGroupsSelect.toString())?"":txtGroupsSelect.toString().substring(0, txtGroupsSelect.length()-1)));
				List<Role> roles = this.roleService.getAllRoleNotInRoleId(ids);
				modelMap.addAttribute("role", roles);
			}else{
				List<Long> empty = new ArrayList<Long>(Arrays.asList(0L));
				List<Role> roles = this.roleService.getAllRoleNotInRoleId(empty);
				modelMap.addAttribute("role", roles);
			}
		}
		return "sys/userAddOrUpdate";
	}
	
	/**
	 * 
	* @Title: addUser 
	* @Description: 新增和更新用户
	* @param @param user
	* @param @return
	* @param @throws SysException    设定文件 
	* @return ResultInfo<User>    返回类型 
	* @throws
	 */
	@RequestMapping("addUser")
	@ResponseBody
	public ResultInfo<User> addUser(User user,@RequestParam(defaultValue = "") String txtGroupsSelect) throws SysException{
		List<Role> roles = new ArrayList<Role>();
		if(!"".equals(txtGroupsSelect)){
			String[] strRoles = txtGroupsSelect.split(",");
			for (String string : strRoles) {
				Role role = this.roleService.getRoleByRoleId(Long.parseLong(string));
				roles.add(role);
			}
		}
		user.setRoles(roles);
		if(user.getUserId()==null||user.getUserId()==0L){//新增
			user.setUserId(0L);
			return userService.addUser(user);
		}else{//更新
			return userService.updateUser(user);
		}
	} 

	/**
	 * 
	* @Title: deleteUser 
	* @Description: 删除用户
	* @param @param userId
	* @param @return
	* @param @throws SysException    设定文件 
	* @return ResultInfo<User>    返回类型 
	* @throws
	 */
	@RequestMapping("deleteUser")
	@ResponseBody
	public ResultInfo<User> deleteUser(Long userId) throws SysException{
		return userService.deleteUserByUserId(userId);
	} 
	
	/**
	 * 分页查询所有角色数据
	 * @param page 页码
	 * @param pageSize 大小
	 * @return
	 */
	@RequestMapping("allUser")
	@ResponseBody
	public Grid<User> getAllUser(@RequestParam("page")int page,@RequestParam("pageSize")int pageSize,@RequestParam("userName")String userName){
		return userService.getAllUser(userName,page-1,pageSize);
	}
	
	
	/**
	 * 保存用户的菜单权限
	 * @param request
	 * @return
	 */
	@RequestMapping("saveUserMenu")
	@ResponseBody
	public ResultInfo<UserMenu> saveUserMenu(@RequestParam(value = "menus") String menus[],@RequestParam("userId")int userId){
		return userService.addUserMenu(menus, userId);
	}
	
	/**
	 * 登录
	 * @return
	 */
	@RequestMapping("login")
	@ResponseBody
	public ResultInfo<String> login(HttpServletRequest request,@RequestParam(value = "loginName") String loginName,@RequestParam(value = "password") String password){
		ResultInfo<User> result = new ResultInfo<User>();
		ResultInfo<String> loginResult = new ResultInfo<String>();
		result = userService.checkLogin(loginName, password);
		if(result.isSuccess()){
			loginResult.setSuccess(true);
			loginResult.setMessage("登录成功！");
			SessionUtils.setSession(request, result.getResult().get("user").get(0));
		}
		return  loginResult;
	}
}
