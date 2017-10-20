package com.ysusoft.basic.spot.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 旅游景区
 * @author songyy
 */
@Entity
@Table(name="t_Base_Spot")
public class Spot {
	@Id
	@GeneratedValue
	private Integer spotId;
	
	private String logoImg;
	private String spotName;
	//所在地名称
	private String location;
	private String locationName;
	private String licenseKey;
	private String licenseNumber;
	private String organizeCode;
	private String grade;
	private String category;
	private String season;
	//负责人
	private String manager;
	//负责人手机号
	private String telphone;
	private String opentime;
	private String price;
	private String description;
	//地址
	private String address;
	
	//景区类型
	private String area;
	private String infrastructure;
	private String spottitle;
	private String landtype;
	private String around;
	private String sightline;
	private String activity;
	private String service;
	private String traffic;
	private String parking;
	private String gas;
	private String brif;
	
	
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Integer getSpotId() {
		return spotId;
	}
	public void setSpotId(Integer spotId) {
		this.spotId = spotId;
	}
	public String getLogoImg() {
		return logoImg;
	}
	public void setLogoImg(String logoImg) {
		this.logoImg = logoImg;
	}
	public String getSpotName() {
		return spotName;
	}
	public void setSpotName(String spotName) {
		this.spotName = spotName;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public String getLicenseKey() {
		return licenseKey;
	}
	public void setLicenseKey(String licenseKey) {
		this.licenseKey = licenseKey;
	}
	public String getLicenseNumber() {
		return licenseNumber;
	}
	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}
	public String getOrganizeCode() {
		return organizeCode;
	}
	public void setOrganizeCode(String organizeCode) {
		this.organizeCode = organizeCode;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getSeason() {
		return season;
	}
	public void setSeason(String season) {
		this.season = season;
	}
	public String getManager() {
		return manager;
	}
	public void setManager(String manager) {
		this.manager = manager;
	}
	public String getTelphone() {
		return telphone;
	}
	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}
	public String getOpentime() {
		return opentime;
	}
	public void setOpentime(String opentime) {
		this.opentime = opentime;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getInfrastructure() {
		return infrastructure;
	}
	public void setInfrastructure(String infrastructure) {
		this.infrastructure = infrastructure;
	}
	public String getSpottitle() {
		return spottitle;
	}
	public void setSpottitle(String spottitle) {
		this.spottitle = spottitle;
	}
	public String getLandtype() {
		return landtype;
	}
	public void setLandtype(String landtype) {
		this.landtype = landtype;
	}
	public String getAround() {
		return around;
	}
	public void setAround(String around) {
		this.around = around;
	}
	public String getSightline() {
		return sightline;
	}
	public void setSightline(String sightline) {
		this.sightline = sightline;
	}
	public String getActivity() {
		return activity;
	}
	public void setActivity(String activity) {
		this.activity = activity;
	}
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public String getTraffic() {
		return traffic;
	}
	public void setTraffic(String traffic) {
		this.traffic = traffic;
	}
	public String getParking() {
		return parking;
	}
	public void setParking(String parking) {
		this.parking = parking;
	}
	public String getGas() {
		return gas;
	}
	public void setGas(String gas) {
		this.gas = gas;
	}
	public String getBrif() {
		return brif;
	}
	public void setBrif(String brif) {
		this.brif = brif;
	}
	
	
}
