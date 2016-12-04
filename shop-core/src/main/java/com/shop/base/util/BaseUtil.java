package com.shop.base.util;

import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

public class BaseUtil {
	private static String webRootPath = null;
	static {
		try {
			webRootPath = getProperties("sysConfig", "web.root.path");
		} catch (Exception e) {
		}
	}

	public static PropertyResourceBundle getProperties(String fileName) {
		return (PropertyResourceBundle) ResourceBundle.getBundle(fileName);
	}

	public static String getProperties(String baseName, String key) {
		return getProperties(baseName).getString(key);
	}

	public static String getWebRootPath() {
		if (webRootPath == null) {
			webRootPath = "";
		}
		return webRootPath;
	}
	
	public static String getIpAddr(){
		return getIpAddr(SpringApplicationContext.getRequest());
	}
	public static String getIpAddr(HttpServletRequest request){
	    String ip = request.getHeader("X-Real-IP");
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
	        ip = request.getHeader("x-forwarded-for");
	    }
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
	        ip = request.getHeader("Proxy-Client-IP");
	    }
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
	        ip = request.getHeader("WL-Proxy-Client-IP");
	    }
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
	        ip = request.getRemoteAddr();
	    }
	    return ip.equals("0:0:0:0:0:0:0:1")?"127.0.0.1":ip;
	}
}
