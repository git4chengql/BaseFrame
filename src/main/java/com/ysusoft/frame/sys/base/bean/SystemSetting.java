package com.ysusoft.frame.sys.base.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author qlcheng
 * @date 2017年5月26日 下午5:14:04
 */
@ConfigurationProperties(prefix = "system")
@Component
public class SystemSetting {

	private String title;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
