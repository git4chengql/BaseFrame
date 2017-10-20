package com.ysusoft.frame.sys.base.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 
* @ClassName: MediaSetting 
* @Description: 多媒体访问
* @author Wuf
* @date 2017年8月10日 
*
 */
@ConfigurationProperties(prefix = "media")
@Component
public class MediaSetting {

	private static String img;
	private static String audio;
	private static String video;
	
	
	public static String getImg() {
		return img;
	}
	public static void setImg(String img) {
		MediaSetting.img = img;
	}
	public static String getAudio() {
		return audio;
	}
	public static void setAudio(String audio) {
		MediaSetting.audio = audio;
	}
	public static String getVideo() {
		return video;
	}
	public static void setVideo(String video) {
		MediaSetting.video = video;
	}
	
}
