package com.ysusoft.frame.sys.base.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author songyy
 * @date 2017年6月9日 下午5:14:04
 */
@ConfigurationProperties(prefix = "upload")
@Component
public class UploadSetting {

	private static String savePath;
	private static String qhdimgServerUrl;
	private static String qhdaudioServerUrl;
	private static String qhdvideoServerUrl;
	
	
	public static String getQhdimgServerUrl() {
		return qhdimgServerUrl;
	}

	public static void setQhdimgServerUrl(String qhdimgServerUrl) {
		UploadSetting.qhdimgServerUrl = qhdimgServerUrl;
	}

	public static String getQhdaudioServerUrl() {
		return qhdaudioServerUrl;
	}

	public static void setQhdaudioServerUrl(String qhdaudioServerUrl) {
		UploadSetting.qhdaudioServerUrl = qhdaudioServerUrl;
	}

	public static String getQhdvideoServerUrl() {
		return qhdvideoServerUrl;
	}

	public static void setQhdvideoServerUrl(String qhdvideoServerUrl) {
		UploadSetting.qhdvideoServerUrl = qhdvideoServerUrl;
	}

	public static String getSavePath() {
		return savePath;
	}

	public static void setSavePath(String savePath) {
		UploadSetting.savePath = savePath;
	}

}
