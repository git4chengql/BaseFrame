package com.ysusoft.frame.sys.util;

import java.util.HashMap;
import java.util.Map;
//对接口进行测试
public class TestMain {
	private String url = "https://api.weixin.qq.com/";
	private String charset = "utf-8";
	private HttpClientUtil httpClientUtil = null;
	
	public TestMain(){
		httpClientUtil = new HttpClientUtil();
	}
	
	public void test(){
		String httpOrgCreateTest = url + "sns/jscode2session";
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("appid","wxe0761ea653eb851a");
		createMap.put("secret","27655f6254c8f26e39c3de68fdfabba3");
		createMap.put("js_code","CODE");
		createMap.put("grant_type","authorization_code");
		String httpOrgCreateTestRtn = httpClientUtil.doPost(httpOrgCreateTest,createMap,charset);
		System.out.println("result:"+httpOrgCreateTestRtn);
	}
	
	public static void main(String[] args){
		TestMain main = new TestMain();
		main.test();
	}
}