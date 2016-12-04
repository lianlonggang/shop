package com.shop.base.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.PropertyResourceBundle;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;


public class StringUtil {
	private static Logger logger = LoggerFactory.getLogger(StringUtil.class);

	public static String unicodeToChina(String utfString){  
	    int i = -1;  
	    int pos = 0;  
	    if(utfString.indexOf("\\u")!=-1){
	    	StringBuilder sb = new StringBuilder();  
	    	
		    while((i=utfString.indexOf("\\u", pos)) != -1){
		        sb.append(utfString.substring(pos, i));  
		        if(i+5 < utfString.length()){  
		            pos = i+6;  
		            sb.append((char)Integer.parseInt(utfString.substring(i+2, i+6), 16));  
		        }
		        if(pos<utfString.length()-2){
			        if(!"\\u".equals(utfString.substring(pos, pos+2))){
			        	int index = utfString.indexOf("\\u", pos);
			        	if(index!=-1){
			        		sb.append(utfString.substring(pos, index));
			        		pos = index;
			        	}
			        	else
			        		sb.append(utfString.substring(pos, utfString.length()));
		 	        }
		        }
		        else
		        	sb.append(utfString.substring(pos, utfString.length()));
		    }
		    return sb.toString().replace("/u", "\\u");
	    }else{
	    	return utfString;
	    }
	}
	/**
	 * 中文转unicode
	 * */
	public static String chinaToUnicode(String str){   
		String result="";   
		for (int i = 0; i < str.length(); i++){   
			int chr1 = (char) str.charAt(i);   
			if(chr1>=19968&&chr1<=171941){//   
				result+="\\u" + Integer.toHexString(chr1);   
			}else{   
				result+=str.charAt(i);   
			}   
		}   
		return result;   
	}
	public static String getRandomCharAndNum(int length) {
		String str = "";
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			boolean b = random.nextBoolean();
			if (b) { // 
				// int choice = random.nextBoolean() ? 65 : 97; 
				b = random.nextBoolean();
				if(b){
					str += (char) (65 + random.nextInt(26));// 
				}else{
					str += (char) (97 + random.nextInt(26));// 
				}
			} else { // 
				str += String.valueOf(random.nextInt(10));
			}
		}
		return str;
	}
	public static String getDate(String formart){
		DateFormat f = new SimpleDateFormat(formart);
		return f.format(new Date());
	}
	public static String formatDate(Date date,String formart){
		DateFormat f = new SimpleDateFormat(formart);
		return f.format(date);
	}
	public static <T> T convertMap2Bean(Class<T> clazz, Map<?, ?> map)
			throws IntrospectionException, IllegalAccessException,
			InstantiationException, InvocationTargetException {
		BeanInfo beanInfo = Introspector.getBeanInfo(clazz); // 获取类属性
		T obj = clazz.newInstance(); // 创建 JavaBean 对象
		// 给 JavaBean 对象的属性赋值
		PropertyDescriptor[] propertyDescriptors =  beanInfo.getPropertyDescriptors();
		for (int i = 0; i< propertyDescriptors.length; i++) {
			PropertyDescriptor descriptor = propertyDescriptors[i];
			String propertyName = descriptor.getName();
			if (map.containsKey(propertyName)) {
				try {
					Object value = map.get(propertyName);
					descriptor.getWriteMethod().invoke(obj, value);
				} catch (Exception e) {
					logger.error(e.getMessage(),e);
				}
			}
		}
		return obj;
	}
    public static <T> Map<String,Object> convertBean2Map(T bean)
            throws IntrospectionException, IllegalAccessException, InvocationTargetException {
        Class<? extends Object> type = bean.getClass();
        Map<String, Object> returnMap = new HashMap<String, Object>();
        BeanInfo beanInfo = Introspector.getBeanInfo(type);

        PropertyDescriptor[] propertyDescriptors =  beanInfo.getPropertyDescriptors();
        for (int i = 0; i< propertyDescriptors.length; i++) {
            PropertyDescriptor descriptor = propertyDescriptors[i];
            String propertyName = descriptor.getName();
            if (!propertyName.equals("class")) {
                Method readMethod = descriptor.getReadMethod();
                Object result = readMethod.invoke(bean, new Object[0]);
                if (result != null) {
                    returnMap.put(propertyName, result);
                } else {
                    returnMap.put(propertyName, "");
                }
            }
        }
        return returnMap;
    }
	public static PropertyResourceBundle getProperties(String fileName){
		return (PropertyResourceBundle)ResourceBundle.getBundle(fileName);
	}
	public static String getProperties(String baseName,String key){
		return getProperties(baseName).getString(key);
	}
	private static String webRootPath=null;
	static{
		try {
			webRootPath=getProperties("conf/sysConfig","web.root.path");
		} catch (Exception e) {
		}
	}
	public static String getWebRootPath() {
		if(webRootPath==null){
			webRootPath="";
		}
		return webRootPath;
	}
	
	/**
	 * 获取webapp 指定目录下的文件列表
	 * */
	public static String getFileList(String filepath){
		File file = new File(SpringApplicationContext.getApplication().getRealPath(filepath));
		String[] fs = null;
		if(file.exists()&&file.isDirectory()){
			fs = file.list();
		}
		return JSONObject.toJSONString(fs);
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
	public static boolean isMatcher(String test,String reg){
		if(test==null){
			return false;
		}
		return Pattern.compile(reg).matcher(test).matches();
	}
	public static String getUri(String url){
		if(isEmpty(url)){
			return url;
		}
		int ind = url.indexOf("?");
		if(ind==-1){
			return url;
		}
		return url.substring(0, ind);
	}
	/***
	 * 获取路径的后缀名 
	 * */
	public static String getExt(String path) {
		if (path == null || "".equals(path.trim())) {
			return "";
		} else if (path.contains(".")) {
			return path.substring(path.lastIndexOf(".")+1, path.length());
		}
		return "";
	}
	public static String[] chars = new String[] { "a", "b", "c", "d", "e", "f",
		"g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
		"t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
		"6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",
		"J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
		"W", "X", "Y", "Z" };


	public static String getUUID() {
		StringBuffer shortBuffer = new StringBuffer();
		String uuid = UUID.randomUUID().toString().replace("-", "");
		for (int i = 0; i < 8; i++) {
			String str = uuid.substring(i * 4, i * 4 + 4);
			int x = Integer.parseInt(str, 16);
			shortBuffer.append(chars[x % 0x3E]);
		}
		return getDate("yyMMddHHmmss")+shortBuffer.toString();

	}
	/**
	 * 获取验证码随机字符串（纯数字）
	 * @param n 验证码长度
	 * @return
	 */
	public static String getNumValidateCode(int n){
		if (n < 1 || n > 10) {
            throw new IllegalArgumentException("cannot random " + n + " bit number");
        }
        Random ran = new Random();
        if (n == 1) {
            return String.valueOf(ran.nextInt(10));
        }
        int bitField = 0;
        char[] chs = new char[n];
        for (int i = 0; i < n; i++) {
            while(true) {
                int k = ran.nextInt(10);
                if( (bitField & (1 << k)) == 0) {
                    bitField |= 1 << k;
                    chs[i] = (char)(k + '0');
                    break;
                }
            }
        }
        return new String(chs);
	}
	/**
	 * 去除字符串中的换行符
	 * @param str
	 * @return
	 */
	public static String replaceLineBreak(String str){
		if(str!=null&&!"".equals(str)){
			str = str.replaceAll("\n", "").replaceAll("\r", "");
		}
		return str;
		
	}
	
	
	/**判断字符串是否为空   
	 * 当字符串为null  或者 长度为0时 返回 true
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(Object obj){
		if(null == obj || "".equals(obj) || "null".equals(obj) || String.valueOf(obj).trim().length()==0){
			return true;
		}else{
			return false;
		}
		
	}
	public static String escapeHtml(String content) {
		if(content==null) return null;        
		//    html = html.replace( "'", "&apos;");
		return  content.replaceAll( "&", "&amp;")
				.replace( "\"", "&quot;")  //"
				.replace( "\t", "&nbsp;&nbsp;")// 替换跳格
				.replace( " ", "&nbsp;")// 替换空格
				.replace("<", "&lt;")
				.replaceAll( ">", "&gt;");

	}
	public static void main(String[] args) {
		System.out.println(getWebRootPath()+"*******");
	}
}
