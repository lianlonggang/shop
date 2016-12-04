package com.shop.base.listener;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shop.base.model.Result;
import com.shop.base.util.FileUtil;
import com.shop.base.util.JSONUtil;
import com.shop.base.util.SpringApplicationContext;
import com.shop.base.util.StringUtil;

public class UrlFilterUtil {
	private static Logger log = LoggerFactory.getLogger(UrlFilterUtil.class);
	private static String cookie = null;//写入cookie的配置
	private static Map<?, ?> urlRoleMap = null;
	private static String page_404_msg=null;
	private static String page_404_url=null;
	private static String page_noright_msg=null;
	private static String page_noright_url=null;
	private static Set<String> passUrl = null;
	private static String loginUrl = null;
	private static String[] isMobileDevice=null;//判断是否手机
	public static void getUrlRoleConfig(){
		urlRoleMap = JSONUtil.convertString2Map(FileUtil.getFileToString("urlFilter.json"));
		if(urlRoleMap!=null){
			Map<String,String> page_404 = (Map<String, String>) urlRoleMap.get("page_404");
			if(page_404!=null){
				page_404_msg = page_404.get("msg");
				page_404_url = page_404.get("redirect_url");
			}
			Map<String,String> page_noright = (Map<String, String>) urlRoleMap.get("page_noright");
			if(page_noright!=null){
				page_noright_msg = page_noright.get("msg");
				page_noright_url = page_noright.get("redirect_url");
			}
			List<String> temp = (List<String>) urlRoleMap.get("passUrl");
			if(temp!=null){
				passUrl = new HashSet<String>(temp);
			}
			cookie=(String)urlRoleMap.get("cookie");
			loginUrl = (String) urlRoleMap.get("loginUrl");
			String str =  (String) urlRoleMap.get("isMobileDevice");
			if(str!=null){
				isMobileDevice=str.split(",");
			}
		}
	}
	public static void doFilter(HttpServletRequest request, HttpServletResponse response,
			FilterChain arg2) throws IOException, ServletException {
		SpringApplicationContext.setRequest(request);
		SpringApplicationContext.setResponse(response);
		String uri = request.getRequestURI();
		if(log.isDebugEnabled()){
			log.debug("request ["+uri+"] from "+StringUtil.getIpAddr());
		}
		String sid = null;
		if(cookie!=null&&StringUtil.isMatcher(uri, cookie)){
			sid = SpringApplicationContext.getSessionIdFromCookie(request);
			if(sid==null){
				sid=request.getSession().getId();
			}
			SpringApplicationContext.setSessionIdFromCookie(response, sid);
		}
		//跳过不需要拦截的url
		if(passUrl!=null){
			for (Iterator<String> iterator = passUrl.iterator(); iterator.hasNext();) {
				String string = (String) iterator.next();
				if(StringUtil.isMatcher(uri, string)){
					myDoFilter(request, response,arg2,uri);
					return ;
				}
			}
		}
		//验证用户权限
//		UserModel user = null;
//		if(sid==null){
//			user=SessionMap.getObj(request);
//		}else{
//			user=SessionMap.getObj(sid);
//		}
//		if(user==null){
//			SpringApplicationContext.clearCookie(request,response);
//			sendRedirect(request,response,loginUrl);
//			return ;
//		}
//		Set<RoleModel> roles = user.getRoles();
//		if(roles==null||roles.size()==0){
//			sendRedirect(request,response,page_noright_url);
//			return ;
//		}
//		for (Iterator<RoleModel> iterator = roles.iterator(); iterator.hasNext();) {
//			RoleModel roleModel = iterator.next();
//			if(roleModel.getRegexp()!=null&&!"".equals(roleModel.getRegexp())){
//				if(StringUtil.isMatcher(uri, roleModel.getRegexp())){
//					myDoFilter(request, response,arg2,uri);
//					return ;
//				}
//			}
//		}
//		Set<FuncModel> list = getUserMenuList(user);
//		for (Iterator<FuncModel> iterator = list.iterator(); iterator.hasNext();) {
//			FuncModel funcModel = iterator.next();
//			if(uri.equals(request.getContextPath()+StringUtil.getUri(funcModel.getFunUrl()))){
//				myDoFilter(request, response,arg2,uri);
//				return ;
//			}
//		}
//		log.warn(user.getUserCode()+"无权限访问===>"+uri);
		sendRedirect(request,response,page_noright_url);

	}
	
	public static void main(String[] args) {
		boolean flag = StringUtil.isMatcher
				("/shop-dubbo-web/joke/saveJokeCommet.htm?commetJokeId=1&commetJokeType=text&userCode=moshang", 
						".*.*");
		System.out.println(flag);
		
	}
	public static void sendRedirect(HttpServletRequest request, HttpServletResponse response,String url)  throws IOException, ServletException{
		String requestType = request.getHeader("X-Requested-With"); 
		String ua = request.getHeader("User-Agent") ; 
		if(requestType!=null||isMobile(ua)){
			Result res = new Result();
			res.setCode(Result.CODE_NO_RIGHT);
			res.setMsg(page_noright_msg);
			response.setContentType("application/json");
			response.setCharacterEncoding("utf-8");
			response.getWriter().write(JSONUtil.converObject2String(res));
			response.getWriter().close();
		}else{
			response.sendRedirect(StringUtil.getWebRootPath()+url);
		}
	}
	private static boolean isMobile(String ua){
		if(isMobileDevice==null||ua==null){
			return false;
		}
		ua=ua.toLowerCase();
		for(int i=0;i<isMobileDevice.length;i++){
			if(ua.indexOf(isMobileDevice[i])>-1){
				return true;
			}
		}
		return false;
	}
	static{
		UrlFilterUtil.getUrlRoleConfig();
	}

	private static void myDoFilter(HttpServletRequest request, HttpServletResponse response,
			FilterChain arg2,String uri) throws IOException, ServletException {
		if(StringUtil.isMatcher(uri, cookie)){
			String sid = SpringApplicationContext.getSessionIdFromCookie(request);
			if(sid!=null){
				SpringApplicationContext.setSessionIdFromCookie(response,sid);
			}
		}
		arg2.doFilter(request, response);
	}
}
