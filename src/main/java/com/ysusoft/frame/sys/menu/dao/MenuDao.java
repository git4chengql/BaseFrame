package com.ysusoft.frame.sys.menu.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.ysusoft.frame.sys.menu.bean.Menu;

/**
 * @author qlcheng
 * @date 2017年5月2日 下午2:41:03
 */
public interface MenuDao extends JpaRepository<Menu,Long> {
	
	/**
	 * 根据标识查询菜单
	 * @param menuId
	 * @return
	 */
	 public Menu findByMenuIdOrderByOrderNumAsc(int menuId);
	 
      /**
       * 根据级别查询菜单	
       * @param level
       * @return
       */
	  public List<Menu> findByLevel(int level);
	  
	  /**
	   * 根据标示删除
	   * @param menuId
	   */
	  public int deleteByMenuId(int menuId);
	  
	  
	  /**
	   * 更新部分字段
	   */
	  @Modifying
	  @Query(value="UPDATE t_sys_menu t SET t.systemId=?2 ,t.parentMenuId=?3,t.LEVEL= ?4,t.isFlag=?5,t.icon=?6,t.title=?7,t.href=?8 " +
	  		"WHERE t.menuId=?1 ",nativeQuery=true)
	  public int updateMenu(int menuId,int systemId,int parentMenuId,int level,int isFlag,String icon,String title,String href);
	  
	  
	  /**
	   * 删除一级菜单下属的二级菜单
	   * @param menuId
	   * @return
	   */
	  public int deleteByParentMenuId(int parentMenuId);
	  
	  /**
	   * 根据上级父节点获取所有子节点
	   * easyUI tree渲染用
	   * @param menuId
	   * @return
	   */
	  @Query(value="select  m.menuId,m.parentMenuId,m.title,m.level,m.href, m.icon,m.isFlag,m.orderNum,m.systemId,(case when"+
	          "  (select  "+
	          "            count(1) "+
	          "        from "+
	          "           t_Sys_Menu d1 "+
	          "        where "+
	          "            d1.parentMenuId = m.menuId) > 0 "+
	          "   then "+
	          "    'closed' "+
	          "  else 'open' "+
	          "  end ) as state,"+
	          "   0 checked "+
	          "  	from "+
	          "  t_Sys_Menu m "+
	          "  	where "+
	          "   m.parentMenuId = ?1 "+
	          "  	order by m.orderNum ",nativeQuery=true)
	  public List<Menu> findByParentMenuId(int menuId);
	  
	  
	  @Query(value=" SELECT t.* FROM t_sys_menu t WHERE t.parentMenuId = ( SELECT m.menuId FROM t_user_menu n LEFT JOIN t_Sys_Menu m ON m.menuId = n.menuId "+
 "WHERE m.level = 0 AND n.userId = ?1  AND m.systemId = ?2 ORDER BY m.orderNum ASC  LIMIT 1) ORDER BY orderNum ASC LIMIT 1",nativeQuery=true)
	  public List<Menu> getFirstMenusByUser(int userId,int systemId);
	  
	  
	  @Query(value="SELECT t.* FROM t_sys_menu t  WHERE t.systemId= ?1",nativeQuery=true)
	  public Menu getMapByUser(int systemId);
	  
	  /**
	   * 查询用户菜单
	   * @param level
	   * @param userId
	   * @return
	   */
	  @Query(value="select m.* from t_user_menu n left join t_Sys_Menu m on m.menuId = n.menuId where m.level = ?1 and n.userId = ?2 and m.parentMenuId = ?3 and m.systemId = ?4 order by m.orderNum asc",nativeQuery=true)
	  public List<Menu> findByLevelAndUser(int level,int userId,int parentId,int systemId);
	  
	  
	  @Query(value="select m.* from t_user_menu n left join t_Sys_Menu m on m.menuId = n.menuId where m.level = ?1 and n.userId = ?2 and m.parentMenuId = ?3 order by m.orderNum asc",nativeQuery=true)
	  public List<Menu> findByLevelAndUserForChild(int level,int userId,int parentId);
	  
	  /**
	   * 用户菜单权限
	   * @param userId
	   * @param menuId
	   * @return
	   */
	  @Query(value="select  m.menuId,m.parentMenuId,m.title,m.level,m.href, m.icon,m.isFlag,m.orderNum,m.systemId,(case when"+
					          "  (select  "+
					          "            count(1) "+
					          "        from "+
					          "           t_Sys_Menu d1 "+
					          "        where "+
					          "            d1.parentMenuId = m.menuId) > 0 "+
					          "   then "+
					          "    'closed' "+
					          "  else 'open' "+
					          "  end ) as state,"+
					          "   (case "+
					          "  when "+
					          "  (select  "+
					          "         count(1) "+
					          "     from "+
					          "         t_User_Menu d1 "+
					          "     where "+
					          "         d1.menuId = M.menuId "+
					          "             and d1.userId = ?1) > 0 "+
					          "  then "+
					          "   1 "+
					          "  else 0 "+
					          "   end) checked "+
					          "  	from "+
					          "  t_Sys_Menu m "+
					          "  	where "+
					          "   m.parentMenuId = ?2 "+
					          "  	order by m.orderNum ",nativeQuery=true)
	  public List<Menu> findAllUserMenus(int userId,int menuId);
	  
	  
	  
	  /**
	   * lpf
	   * 角色菜单权限管理
	   * @param roleId
	   * @param menuId
	   * @return
	   */
	  @Query(value= "select  m.menuId,m.parentMenuId,m.title,m.level,m.href, m.icon,m.isFlag,m.orderNum,systemId,(case when "+
					          "  (select  "+
					          "        count(1)  "+
					          "      from  "+
					          "         t_Sys_Menu d1  "+
					          "      where  "+
					          "          d1.parentMenuId = m.menuId) > 0  "+
					          "   then  "+
					          "    'closed'  "+
					          "  else 'open'  "+
					          "  end ) as state,"+
					          "   (case  "+
					          "   when  "+
					          "  (select   "+
					          "         count(1)   "+
					          "     from  "+
					          "        t_role_menu d1  "+
					          "   where  "+
					          "         d1.menuId = M.menuId  "+
					          "            and d1.roleId = ?1 )>0 "+
					          "   then  "+
					          "    1  "+
					          "  else 0 "+
					          "   end) checked "+
					          "  	from "+
					          "  t_Sys_Menu m "+
					          " 	where "+
					          "   m.parentMenuId =?2 "+
					          "  order by m.orderNum",nativeQuery=true)
	  public List<Menu> findAllRoleMenus(int roleId,int menuId);
	  
	  
	  
	  
}
