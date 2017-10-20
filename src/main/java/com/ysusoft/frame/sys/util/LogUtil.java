/**
 * 
 */
package com.ysusoft.frame.sys.util;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.ysusoft.frame.sys.base.bean.YsuLog;

/**
 * @author qlcheng
 * @date 2017年10月11日 上午10:25:40
 */
public class LogUtil {

	public static YsuLog getLog(HttpServletRequest req) {
		YsuLog log = new YsuLog();
		String sessionId = req.getRequestedSessionId();
		String url = req.getRequestURI();
		String parameter = JSON.toJSONString(req.getParameterMap(), SerializerFeature.DisableCircularReferenceDetect,
				SerializerFeature.WriteMapNullValue);
		log.setReqSession(sessionId);
		log.setReqUri(url);
		log.setReqParameters(parameter);
		log.setReqMethod(req.getMethod());
		log.setReqType(LogUtil.getRequestType(req));
		log.setClientIp(LogUtil.getIpAddress(req));
		log.setReqTime(new Timestamp(System.currentTimeMillis()));
		req.setAttribute("reqTime", System.currentTimeMillis());
		return log;
	}

	public static String getRequestType(HttpServletRequest request) {
        return request.getHeader("X-Requested-With");
    }
	
	public final static String getIpAddress(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.trim() == "" || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.trim() == "" || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.trim() == "" || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        // 多个路由时，取第一个非unknown的ip
        final String[] arr = ip.split(",");
        for (final String str : arr) {
            if (!"unknown".equalsIgnoreCase(str)) {
                ip = str;
                break;
            }
        }
        return ip;
	}
}
