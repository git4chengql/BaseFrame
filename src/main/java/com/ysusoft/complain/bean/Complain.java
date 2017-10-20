package com.ysusoft.complain.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OrderBy;
import javax.persistence.Table;

/**
 * 投诉
 * @author songyy
 */
@Entity
@Table(name="t_Complain")
public class Complain {
	@Id
	@GeneratedValue
	private Integer complainId;
	
	private String userName;
	private String telphone;
	@Column(length=500)
	private String title;
	@Column(length=2000)
	private String content;
	private String code;
	private String backInfo;

	//投诉创建时间
	@OrderBy(value = "createDate DESC")
	private Date createDate;
	
	//来源类型，如 微信：wx ,旅游委采集：cj
	private String type;
	//分来源用户表示，如微信中的唯一标识
	private String userId;
	private String images;
	private String audios;
	private String voices;
	
	//时限类型
	private String complainType;
	
	//存放整条投诉的概括结果信息
	private String resultMsg;
	
	public String getBackInfo() {
		return backInfo;
	}
	public void setBackInfo(String backInfo) {
		this.backInfo = backInfo;
	}
	public Integer getComplainId() {
		return complainId;
	}
	public void setComplainId(Integer complainId) {
		this.complainId = complainId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getTelphone() {
		return telphone;
	}
	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getImages() {
		return images;
	}
	public void setImages(String images) {
		this.images = images;
	}
	public String getAudios() {
		return audios;
	}
	public void setAudios(String audios) {
		this.audios = audios;
	}
	public String getResultMsg() {
		return resultMsg;
	}
	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}
	public String getComplainType() {
		return complainType;
	}
	public void setComplainType(String complainType) {
		this.complainType = complainType;
	}
	public String getVoices() {
		return voices;
	}
	public void setVoices(String voices) {
		this.voices = voices;
	}
}
