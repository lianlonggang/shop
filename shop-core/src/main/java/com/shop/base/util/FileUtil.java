package com.shop.base.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileUtil {
	private static Logger log = LoggerFactory.getLogger(FileUtil.class);
	public static String getFileToString(String filePath){
		return getStringFromInputStream(getClassPathFileStream(filePath));
	}
	public static InputStream getClassPathFileStream(String filePath){
		return FileUtil.class.getResourceAsStream("/"+filePath);
	}
	public static String getStringFromInputStream(InputStream input){
		return getStringFromInputStream(input,"utf-8");
	}
	public static String getStringFromInputStream(InputStream input,String code){
		BufferedReader reader =null;
		try {
			reader = new BufferedReader(new InputStreamReader(input, code));
			String temp = null;
			StringBuffer sb = new StringBuffer();
			while((temp=reader.readLine())!=null){
				sb.append(temp);
			}
			return sb.toString();
		} catch (Exception e) {
			log.error("reader inputStream error :", e);
		}finally{
			if(reader!=null){
				try {
					reader.close();
				} catch (IOException e) {
					log.error("close inputStream error :", e);
				}
			}
		}
		return null;
	}
}
