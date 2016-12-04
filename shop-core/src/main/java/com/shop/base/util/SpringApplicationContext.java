package com.shop.base.util;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class SpringApplicationContext {
	private static Logger logger = LoggerFactory.getLogger(SpringApplicationContext.class);
	private static ThreadLocal<HttpServletRequest> requestLocal = new ThreadLocal<HttpServletRequest>();
	
	//用来获取项目路径
	private static ServletContext application = null;
	
	public static ServletContext getApplication() {
		return application;
	}
	public static void setApplication(ServletContext application) {
		SpringApplicationContext.application = application;
	}
	private static ThreadLocal<HttpServletResponse> responseLocal = new ThreadLocal<HttpServletResponse>();
	public static HttpServletRequest getRequest() {
		return requestLocal.get();
	}

	public static void setRequest(HttpServletRequest request) {
		requestLocal.set(request);
	}

	public static HttpServletResponse getResponse() {
		return responseLocal.get();
	}

	public static void setResponse(HttpServletResponse response) {
		responseLocal.set(response);
	}
	private static ApplicationContext ac = null;
	public static void setApplicationContext(ServletContext sc){
		ac = WebApplicationContextUtils.getWebApplicationContext(sc);
	}
	public static void setClassPathXmlApplicationContext(String xml){
		ac = new ClassPathXmlApplicationContext(xml);
	}
	public static void setFileSystemXmlApplicationContext(String xml){
		ac = new FileSystemXmlApplicationContext(xml);
	}
	public static ApplicationContext getApplicationContext(){
		return ac;
	}
	public static<T> T getApplicationContextBean(Class<T> clzz){
		if(ac!=null){
			logger.debug(clzz.toString());
			return ac.getBean(clzz);
		}
		return null;
	}
	public static String getSessionId(){
		return getSessionId(null);
	}
	public static String getSessionIdFromCookie(HttpServletRequest request){
		if(request ==null){
			request = getRequest();
		}
		String sid =request.getHeader(Contants.SESSION_ID);
		if(sid==null){
			Cookie[] cs =request.getCookies();
			if(cs!=null&&cs.length>0){
				for (int i = 0; i < cs.length; i++) {
					Cookie c = cs[i];
					if(Contants.SESSION_ID.equals(c.getName())){
						sid = c.getValue();
						break ;
					}
				}
			}
		}
		return sid;
	}
	public static void setSessionIdFromCookie(HttpServletResponse response,String sid){
		if(response==null){
			response=getResponse();
		}
		Cookie cookie = new Cookie(Contants.SESSION_ID, sid);
		cookie.setHttpOnly(true);
		cookie.setPath(Contants.COOKIE_PATH);
		response.addCookie(cookie);
	}
	public static void clearCookie(HttpServletRequest request,HttpServletResponse response){
		try{  
			Cookie cookie = new Cookie(Contants.SESSION_ID, null);
			cookie.setHttpOnly(true);
			cookie.setMaxAge(0);
			cookie.setPath(Contants.COOKIE_PATH);
			response.addCookie(cookie);
		}catch(Exception ex)  {  
		} 
	}
	public static String getSessionId(HttpServletRequest request){
		return getSessionId(request,null);
	}
	public static String getSessionId(HttpServletRequest request,HttpServletResponse response){
		if(request==null){
			request=getRequest();
		}
		String sid = getSessionIdFromCookie(request);
		if(sid ==null||"".equals(sid.trim())){
			sid = request.getSession().getId();
			setSessionIdFromCookie(response,sid);
		}
		return sid;
	}
}
