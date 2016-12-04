package com.shop.base.util;

import redis.clients.jedis.tool.JedisTool;

import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class CacheTool {
	private static boolean enable = false;

	private static PropertyResourceBundle getProperties(String fileName) {
		return (PropertyResourceBundle) ResourceBundle.getBundle(fileName);
	}

	static {
		PropertyResourceBundle config = getProperties("redis");
		try {
			enable = Boolean.parseBoolean(config.getString("enable"));
		} catch (Exception e1) {
			enable = false;
		}
		if (enable) {
			JedisTool.getShardedJedisPool();
		}
	}

	public static boolean isEnable() {
		return enable;
	}
}
