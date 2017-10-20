package com.ysusoft.frame.sys.role.service;

import java.util.List;

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
import com.ysusoft.frame.sys.role.bean.Role;
import com.ysusoft.frame.sys.role.bean.RoleMenu;
import com.ysusoft.frame.sys.role.dao.RoleDao;
import com.ysusoft.frame.sys.role.dao.RoleMenuDao;

/**
 * 角色管理服务层
 * @author qlcheng
 * @date 2017年4月11日 下午1:57:03
 */

@Service
public class RoleService {

	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private RoleMenuDao menuDao;
	
	private Sort sort = new Sort(Sort.Direction.ASC, "roleId");  
	
	private Pageable pageable;
	
	private Page<Role> pages;
	
	private ResultInfo<Role> rs = new ResultInfo<Role>();
	
	public static final String ROLE_CACHE_NAME = "syscache";
	
	
	/**
	 * 新增角色
	 * @param role 角色信息
	 * @return 固定JSON结果
	 */
	@CacheEvict(value=ROLE_CACHE_NAME,allEntries=true)
	public ResultInfo<Role> addRole(Role role){
		Role r = roleDao.save(role);
		if(r!=null){
			rs.setSuccess(true);
			rs.setMessage("保存成功");
		}
		return rs;
	}
	
	/**
	 * 修改角色信息
	 * @param role
	 * @return 角色信息
	 */
	@CacheEvict(value=ROLE_CACHE_NAME,allEntries=true)
	@Transactional
	public ResultInfo<Role> updateRole(Role role){
		if(roleDao.updateByRoleId(role.getRoleId(),role.getRoleName())>0){
			rs.setSuccess(true);
			rs.setMessage("更新成功");
		}else{
			rs.setMessage("更新失败");
		}
		return rs;
	}
	
	/**
	 * 根据角色标识删除角色
	 * @param roleId 角色标识
	 */
	@Transactional
	@CacheEvict(value=ROLE_CACHE_NAME,allEntries=true)
	public ResultInfo<Role> deleteRole(String fidList[]){
		for (String roleId:fidList) {
			if(roleDao.deleteByRoleId(Long.parseLong(roleId))>0){
				rs.setSuccess(true);
				rs.setMessage("删除成功");
			}else{
				rs.setMessage("删除失败");
			}
		}
		return rs;
	}
	
	
	/**
	 * 根据角色名称查询角色
	 * @param roleName 角色名称
	 * @return
	 */
	public Role getRoleByRoleName(String roleName){
		return roleDao.findByRoleName(roleName);
	}
	
	/**
	 * 根据角色ID查询角色
	 * @param roleName 角色名称
	 * @return
	 */
	public Role getRoleByRoleId(Long roleId){
		return roleDao.findOne(roleId);
	}
	
	/**
	 * 查询所有角色
	 * @return 
	 */
    @Cacheable(value=ROLE_CACHE_NAME,key="'role_'+#roleName+#page+#pageSize")
	public Grid<Role> getAllRole(String roleName,int page,int pageSize){
	    pageable = new PageRequest(page, pageSize, sort); 
	    if(roleName!="")
		      pages = roleDao.findByRoleNameLike("%"+roleName+"%",pageable); 
		    else
		    	  pages= roleDao.findByRoleNameNotNull(pageable);
	    Grid<Role> roles = new Grid<Role>();
		if(pages.getSize()>0){
			roles.setRows(pages.getContent());
			roles.setTotal(pages.getTotalElements());
		}
		return roles;
	}
	
    public List<Role> getAllRoleNotInRoleId(List<Long> ids){
    	return this.roleDao.findByRoleIdNotIn(ids);
    }
    
    
    
    
    /**
     * 删除所有菜单然后保存新增加的菜单
     * @param menusList
     * @param roleId
     * @return
     */
    public ResultInfo<RoleMenu> addRoleMenu(String []menusList,int roleId){
    	ResultInfo<RoleMenu> rm=new ResultInfo<RoleMenu>();
    	if(menuDao.deleteByRoleId(roleId)>=0){
    		for (String menuid:menusList) {
    			RoleMenu roleMenu=new RoleMenu();
    			roleMenu.setMenuId(Integer.parseInt(menuid));
    			roleMenu.setRoleId(roleId);
    			roleMenu=menuDao.save(roleMenu);
    			if(roleMenu.getId()>0){
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
    
    
}
