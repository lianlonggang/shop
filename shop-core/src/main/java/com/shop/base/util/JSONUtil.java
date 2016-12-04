package com.shop.base.util;

import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class JSONUtil {
	public static Map<?, ?> convertString2Map(String str){
		if(str == null){
			return null;
		}
		return JSON.parseObject(str, Map.class);
	}
	public static String converObject2String(Object obj){
		return JSON.toJSONString(obj);
	}
	public static JSONObject convertString2JsonObject(String str){
		if(str == null){
			return null;
		}
		return JSON.parseObject(str);
	}
	public static JSONArray convertString2JsonArray(String str){
		if(str == null){
			return null;
		}
		return JSON.parseArray(str);
	}
	public static Object convertObject2JsonObject(Object obj){
		if(obj == null){
			return null;
		}
		return JSONObject.toJSON(obj);
	}
}
