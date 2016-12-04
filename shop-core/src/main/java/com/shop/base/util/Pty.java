package com.shop.base.util;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 配置文件读取帮助类
 * */
public final class Pty {
	private static final Logger LOGGER = LoggerFactory.getLogger(Pty.class);
	
	private static Map<String, Pty> maps = new ConcurrentHashMap<String, Pty>();
	
	private static int refreshCount = 0;  //内容重新读取次数标识，其他调用程序检测是否配置更新 进行相应的调整   
	public static int getRefreshCount(){
		return refreshCount;
	}
	
	/**
	 * 从缓存对象中获取当前对象实例，
	 * @param file 配置文件名
	 * */
	public static Pty get(String file){
		if(maps.containsKey(file)){
			return maps.get(file);
		}else {
			Pty env = new Pty(file);
			maps.put(file, env);
			return env;
		}
	}
	
	/**
	 * 刷新Map集合
	 * */
	public static void refresh(){
		Iterator<Map.Entry<String, Pty>> its = maps.entrySet().iterator();
		Map.Entry<String, Pty> entry = null;
		while (its.hasNext()) {
			entry = its.next();
			entry.getValue().close();
		}
		refreshCount++;
	}
	
	InputStream ins = null;
	Properties pro = null;
	String file = null; 
	
	private Pty(String file){
		try {
			this.file = file;
			LOGGER.debug("读取配置文件信息："+file);
			ins = Pty.class.getClassLoader().getResourceAsStream(file+".properties");
			pro = new Properties();
			pro.load(ins);
		} catch (IOException e) {
			LOGGER.error("配置文件读取错误："+file+".properties读取失败！",e);
			throw new RuntimeException("环境变量配置文件："+file+".properties读取失败！",e);
		}finally{
			if(ins!=null){
				try {
					ins.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public String getProp(String key){
		String val = pro.getProperty(key);
		return val;
	}	
	
	public void close(){
		if(ins!=null)
			try {
				ins.close();
				LOGGER.debug("配置文件读取流正常关闭！");
			} catch (IOException e) {
				LOGGER.error("配置文件流关闭失败！",e);
				throw new RuntimeException("配置文件流关闭失败！",e);
			} finally {
				maps.remove(file);
			}
	}
}