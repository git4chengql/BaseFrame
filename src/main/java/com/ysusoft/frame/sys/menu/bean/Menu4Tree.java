package com.ysusoft.frame.sys.menu.bean;

/**
 * @author qlcheng
 * @date 2017年5月11日 下午3:17:19
 */
public class Menu4Tree {
	private int id;
	private String text;
	private int pId;
	private String state;
	private int level;
	private int attributes;
	private boolean checked;
	private String acode;
	private String iconCls;
	private int systemId;
	
	
	public int getSystemId() {
		return systemId;
	}
	public void setSystemId(int systemId) {
		this.systemId = systemId;
	}
	public String getIconCls() {
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	public String getAcode() {
		return acode;
	}
	public void setAcode(String acode) {
		this.acode = acode;
	}
	public boolean getChecked() {
		return checked;
	}
	public void setChecked(int checked) {
		this.checked = checked==1;
	}
	public String getAttributes() {
		return "{\"level\":\""+attributes+"\",\"systemId\":\""+systemId+"\"}";
	}
	public void setAttributes(int attributes) {
		this.attributes = attributes;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getpId() {
		return pId;
	}
	public void setpId(int pId) {
		this.pId = pId;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
   
}
