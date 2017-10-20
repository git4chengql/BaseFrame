package com.ysusoft.frame.sys.user.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ysusoft.frame.sys.base.bean.Grid;
import com.ysusoft.frame.sys.base.bean.ResultInfo;
import com.ysusoft.frame.sys.exception.SysException;
import com.ysusoft.frame.sys.user.bean.User;
import com.ysusoft.frame.sys.user.bean.UserMenu;
import com.ysusoft.frame.sys.user.dao.UserDao;
import com.ysusoft.frame.sys.user.dao.UserMenuDao;

/**
 * @author qlcheng
 * @date 2017年4月10日 下午3:22:29
 */
@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserMenuDao userMenuDao;

	private Sort sort = new Sort(Sort.Direction.ASC, "userId");  
	
	private Pageable pageable;
	
	private Page<User> pages;
	
	public static final String USER_CACHE_NAME = "syscache";
	
	private ResultInfo<User> resultInfo = new ResultInfo<User>();
	
	/**
	 * 
	* @Title: addUser 
	* @Description: 新增用户
	* @param @param user
	* @param @return
	* @param @throws SysException    设定文件 
	* @return ResultInfo<User>    返回类型 
	* @throws
	 */
	@CacheEvict(value=USER_CACHE_NAME,allEntries=true)
	public ResultInfo<User> addUser(User user) throws SysException{
		User save = this.userDao.save(user);
		if(save!=null){
			resultInfo.setSuccess(true);
			resultInfo.setMessage("保存成功");
		}else{
			resultInfo.setMessage("保存失败");
		}
		return resultInfo;
	}
	
	/**
	 * 
	* @Title: deleteUserByUserId 
	* @Description: 根据UserId删除用户记录
	* @param @param userId 用户Id
	* @param @return
	* @param @throws SysException    设定文件 
	* @return ResultInfo<User>    返回类型 
	* @throws
	 */
	@Transactional
	@CacheEvict(value=USER_CACHE_NAME,allEntries=true)
	public ResultInfo<User> deleteUserByUserId(long userId) throws SysException{
		if(userDao.deleteByUserId(userId)>0){
			resultInfo.setSuccess(true);
			resultInfo.setMessage("删除成功");
		}else{
			resultInfo.setMessage("删除失败");
		}
		return resultInfo;
	}
	
	/**
	 * 
	* @Title: updateUser 
	* @Description: 根据ID更新用户
	* @param @param user
	* @param @return
	* @param @throws SysException    设定文件 
	* @return ResultInfo<User>    返回类型 
	* @throws
	 */
	@CacheEvict(value=USER_CACHE_NAME,allEntries=true)
	public ResultInfo<User> updateUser(User user) throws SysException{
		User findOne = this.userDao.findOne(user.getUserId());
		findOne.setLoginName(user.getLoginName());
		findOne.setUserName(user.getUserName());
		findOne.setRoles(user.getRoles());
		User r = this.userDao.save(findOne);
		if(r!=null){
			resultInfo.setSuccess(true);
			resultInfo.setMessage("更新成功");
		}else{
			resultInfo.setMessage("更新失败");
		}
		return resultInfo;
	}
	

	/**
	 * 查询所有用户
	 * @return 
	 */
    @Cacheable(value=USER_CACHE_NAME,key="'user_'+#userName+#page+#pageSize")
	public Grid<User> getAllUser(String userName,int page,int pageSize){
	    pageable = new PageRequest(page, pageSize, sort); 
	    if(userName!="")
		      pages = userDao.findByUserNameLike("%"+userName+"%",pageable); 
		    else
		      pages= userDao.findByUserNameNotNull(pageable);
	    Grid<User> users = new Grid<User>();
		if(pages.getSize()>0){
			users.setRows(pages.getContent());
			users.setTotal(pages.getTotalElements());
		}
		return users;
	}
    
	public User getUserByUserId(long userid){
		return this.userDao.findOne(userid);
	}
	
	
	/**
	 * 删除用户的菜单，重新保存新增加的菜单权限
	 * @param menusList
	 * @param userId
	 * @return
	 */
	public ResultInfo<UserMenu> addUserMenu(String []menusList,int userId){
	    ResultInfo<UserMenu> rm=new ResultInfo<UserMenu>();
	    if(userMenuDao.deleteByUserId(userId)>=0){
	    	for (String menuid:menusList) {
	    			UserMenu userMenu=new UserMenu();
	    			userMenu.setMenuId(Integer.parseInt(menuid));
	    			userMenu.setUserId(userId);
	    			userMenu=userMenuDao.save(userMenu);
	    			if(userMenu.getId()>0){
	    				rm.setSuccess(true);
	    				rm.setMessage("操作成功！");
	    			}
	    		}
	    	}else {
				rm.setSuccess(false);
				rm.setMessage("操作失败！");
			}
			return rm;
		}
	
	/**
	 * 检测登录
	 * @param userName
	 * @param pwd
	 * @return
	 */
	public ResultInfo<User> checkLogin(String loginName,String password){
		ResultInfo<User> rm=new ResultInfo<User>();
		List<User> user = userDao.findByLoginNameAndPassword(loginName, password);
		if(user.size()==1){
			Map<String,List<User>> map = new HashMap<String,List<User>>();
			rm.setMessage("登录成功");
			rm.setSuccess(true);
			map.put("user", user);
			rm.setResult(map);
		}
		return rm;
	}
}
