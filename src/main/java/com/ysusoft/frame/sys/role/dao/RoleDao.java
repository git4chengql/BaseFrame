package com.ysusoft.frame.sys.role.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.ysusoft.frame.sys.role.bean.Role;

/**
 *  角色DAO
 * @author qlcheng
 * @date 2017年4月11日 下午1:57:03
 */

public interface RoleDao extends JpaRepository<Role, Long> {

	/**
	 *  根据角色名称查询
	 * @param roleName
	 * @return
	 */
	public Role findByRoleName(String roleName);
	
	/**
	 * 根据标示删除
	 * @param roleId
	 */
	public int deleteByRoleId(Long roleId);
	
	
	
	/**
	 * 根据角色标识更新角色
	 * @param roleId
	 * @return
	 */
	@Query("update Role r SET r.roleName=?2 where r.roleId=?1")
	@Modifying
	public int updateByRoleId(Long roleId,String roleName);
	
	/**
	 * 根据名称分页查询
	 * @param name
	 * @param pageable
	 * @return
	 */
	Page<Role> findByRoleNameLike(String name,Pageable pageable); 
	
	/**
	 * 分页查询所有
	 * @param pageable
	 * @return
	 */
	Page<Role> findByRoleNameNotNull(Pageable pageable);
	
	/**
	 * 
	* @Title: findByRoleIdNotIn 
	* @Description: 获取所有不在参数中的角色
	* @param @param ids
	* @param @return    设定文件 
	* @return List<Role>    返回类型 
	* @throws
	 */
	List<Role> findByRoleIdNotIn(List<Long> ids);
}
