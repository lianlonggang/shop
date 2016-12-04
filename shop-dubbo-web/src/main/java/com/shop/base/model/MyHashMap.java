package com.shop.base.model;

import java.util.HashMap;

public class MyHashMap<K, V> extends HashMap<K, V> {

	private static final long serialVersionUID = -7875451841265634870L;

	@Override
	public V put(K key, V value) {
		if (key instanceof String) {
			if(key!=null)
				key=(K) ((String) key).toLowerCase();
		}
		return super.put(key, value);
	}


}
