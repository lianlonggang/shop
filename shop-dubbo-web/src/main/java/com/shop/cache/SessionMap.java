package com.shop.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shop.base.entity.ShopUser;
import com.shop.base.util.CacheTool;
import com.shop.base.util.SpringApplicationContext;

import redis.clients.jedis.tool.JedisTool;

/**
 * 保存用户登录状态的信息
 * 
 * 使用后及时清理
 */
public final class SessionMap {

	private static final Logger LOGGER = LoggerFactory.getLogger(SessionMap.class);

	private static final Map<String, ShopUser> sessionmap = new ConcurrentHashMap<String, ShopUser>();

	private SessionMap() {
	};

	/**
	 * 获取默认 保存对象 用户实体类
	 */
	public static ShopUser getObj() {
		return getObj((HttpServletRequest) null);
	}

	/**
	 * 获取默认 保存对象 用户实体类
	 */
	public static ShopUser getObj(HttpServletRequest request) {
		String sessionid = request == null ? SpringApplicationContext.getSessionId()
				: SpringApplicationContext.getSessionId(request);
		return getObj(sessionid);
	}

	/**
	 * 获取默认 保存对象 用户实体类
	 */
	public static ShopUser getObj(String sessionid) {
		if (CacheTool.isEnable()) {
			try {
				return getObjById(sessionid);
			} catch (Exception e) {
				LOGGER.error("get ShopUser error ", e);
				return null;
			}
		} else {
			return sessionmap.get(sessionid);
		}
	}

	public static ShopUser getObjById(String sid) {
		try {
			return (ShopUser) JedisTool.getObject(sid);
		} catch (Exception e) {
			LOGGER.error("get ShopUser error ", e);
			return null;
		}
	}

	/**
	 * 保存默认对象 用户实体类
	 */
	public static void putObj(ShopUser obj) {
		String sessionid = SpringApplicationContext.getSessionId();
		if (CacheTool.isEnable()) {
			// setRedisObject(sessionid,obj);
			try {
				JedisTool.setObject(sessionid, obj, new Integer(60 * 60 * 12));
			} catch (Exception e) {
				LOGGER.error("put ShopUser error ", e);
			}
		} else {
			sessionmap.put(sessionid, obj);
		}
	}

	/**
	 * 销毁session对应的所有信息
	 */
	public static void remove(String sessionId) {
		if (CacheTool.isEnable()) {
			try {
				JedisTool.del(sessionId);
			} catch (Exception e) {
				LOGGER.error("remove data from redis error", e);
			}
		} else {
			sessionmap.remove(sessionId);
		}
	}
}
