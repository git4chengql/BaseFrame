package com.ysusoft.basic.guides.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.ysusoft.basic.travel.bean.Travel;
import com.ysusoft.frame.common.edu.bean.Edu;
import com.ysusoft.frame.common.nation.bean.Nation;

/**
 * 导游
 * @author songyy
 */
@Entity
@Table(name="t_Base_Guides")
public class Guides {
	@Id
	@GeneratedValue
	private Integer guideId;
	//导游姓名
	private String guideName;

	private String sex;
	
	private String birth;
	
	private String idCard;
	
	private String telphone;
	
	private String imgName;
	//导游证号
	private String guideNumber;
	//导游卡号
	private String cardNumber;
	//所在城市
	private String locationCity;
	//所在城市名称
	private String cityName;
	//语种
	private String language;
	//导游等级
	private String guideClass;
	//资格证号
	private String qualifyNumber;
	//发证机构
	private String licensingBody;
	//发证日期
	private String licensingDate;
	//年审情况
	private String annualSituation;
	//奖惩信息
	private String valuesInfo;
	//培训信息
	private String training;
	//出团情况
	private String groupTour;
	
	//民族
	@OneToOne(optional=true)
	@JoinColumn(name="nationId")
	private Nation nation;
	
	//学历
	@OneToOne(optional=true)
	@JoinColumn(name="eduId")
	private Edu edu;
	
	//所属旅行社
	@OneToOne(optional=true)
	@JoinColumn(name="travelId")
	private Travel travel;
	
	
	
	public Nation getNation() {
		return nation;
	}

	public void setNation(Nation nation) {
		this.nation = nation;
	}

	public Edu getEdu() {
		return edu;
	}

	public void setEdu(Edu edu) {
		this.edu = edu;
	}

	public Travel getTravel() {
		return travel;
	}

	public void setTravel(Travel travel) {
		this.travel = travel;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getLocationCity() {
		return locationCity;
	}

	public void setLocationCity(String locationCity) {
		this.locationCity = locationCity;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getGuideClass() {
		return guideClass;
	}

	public void setGuideClass(String guideClass) {
		this.guideClass = guideClass;
	}

	
	public String getQualifyNumber() {
		return qualifyNumber;
	}

	public void setQualifyNumber(String qualifyNumber) {
		this.qualifyNumber = qualifyNumber;
	}

	public String getLicensingBody() {
		return licensingBody;
	}

	public void setLicensingBody(String licensingBody) {
		this.licensingBody = licensingBody;
	}

	public String getLicensingDate() {
		return licensingDate;
	}

	public void setLicensingDate(String licensingDate) {
		this.licensingDate = licensingDate;
	}

	public String getAnnualSituation() {
		return annualSituation;
	}

	public void setAnnualSituation(String annualSituation) {
		this.annualSituation = annualSituation;
	}

	
	public String getValuesInfo() {
		return valuesInfo;
	}

	public void setValuesInfo(String valuesInfo) {
		this.valuesInfo = valuesInfo;
	}

	public String getTraining() {
		return training;
	}

	public void setTraining(String training) {
		this.training = training;
	}

	public String getGroupTour() {
		return groupTour;
	}

	public void setGroupTour(String groupTour) {
		this.groupTour = groupTour;
	}

	public String getGuideNumber() {
		return guideNumber;
	}

	public void setGuideNumber(String guideNumber) {
		this.guideNumber = guideNumber;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getImgName() {
		return imgName;
	}

	public void setImgName(String imgName) {
		this.imgName = imgName;
	}

	public Integer getGuideId() {
		return guideId;
	}

	public void setGuideId(Integer guideId) {
		this.guideId = guideId;
	}

	public String getGuideName() {
		return guideName;
	}

	public void setGuideName(String guideName) {
		this.guideName = guideName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}
	
	
}
