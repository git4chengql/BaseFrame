package com.ysusoft.frame.sys.menu.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author songyy
 * @date 2017年9月6日 
 */

@Entity
@Table(name = "t_sys_system")
public class AllSystem {

	@Id
	@GeneratedValue
	private int systemId;
	private String systemName;
	
	
	public int getSystemId() {
		return systemId;
	}
	public void setSystemId(int systemId) {
		this.systemId = systemId;
	}
	public String getSystemName() {
		return systemName;
	}
	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}
}
