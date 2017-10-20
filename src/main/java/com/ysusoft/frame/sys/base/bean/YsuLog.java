package com.ysusoft.frame.sys.base.bean;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author qlcheng
 * @date 2017年10月11日 上午9:49:17 日志
 */
@Entity
@Table(name = "t_sys_logs")
public class YsuLog {

	@Id
	@GeneratedValue
	private long id;
	private String clientIp;
	private String reqUri;
	private String reqType;
	private String reqMethod;
	private String reqParameters;
	private String reqSession;
	private Timestamp reqTime;
	private Timestamp respTime;
	private String respData;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getClientIp() {
		return clientIp;
	}

	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}

	public String getReqUri() {
		return reqUri;
	}

	public void setReqUri(String reqUri) {
		this.reqUri = reqUri;
	}

	public String getReqType() {
		return reqType;
	}

	public void setReqType(String reqType) {
		this.reqType = reqType;
	}

	public String getReqMethod() {
		return reqMethod;
	}

	public void setReqMethod(String reqMethod) {
		this.reqMethod = reqMethod;
	}

	public String getReqParameters() {
		return reqParameters;
	}

	public void setReqParameters(String reqParameters) {
		this.reqParameters = reqParameters;
	}

	public String getReqSession() {
		return reqSession;
	}

	public void setReqSession(String reqSession) {
		this.reqSession = reqSession;
	}

	public Timestamp getReqTime() {
		return reqTime;
	}

	public void setReqTime(Timestamp reqTime) {
		this.reqTime = reqTime;
	}

	public Timestamp getRespTime() {
		return respTime;
	}

	public void setRespTime(Timestamp respTime) {
		this.respTime = respTime;
	}

	public String getRespData() {
		return respData;
	}

	public void setRespData(String respData) {
		this.respData = respData;
	}

}
