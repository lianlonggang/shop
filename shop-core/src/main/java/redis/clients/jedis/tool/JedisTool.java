package redis.clients.jedis.tool;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.JedisPoolConfig;  
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

public class JedisTool {  
	private static Logger log = LoggerFactory.getLogger(JedisTool.class);
	//    private static JedisPool pool = null;  
	private static ShardedJedisPool pool = null;    
	/** 
	 */
	private static String host = "192.168.1.254";
	private static int port =6379;
	private static int timeout =20000;
	private static int weight=1;
	private static boolean enable=false;
	private static PropertyResourceBundle getProperties(String fileName){
		return (PropertyResourceBundle)ResourceBundle.getBundle(fileName);
	}
	static {
		PropertyResourceBundle config = getProperties("redis");
		log.debug("get config from redis");
		try {
			enable = Boolean.parseBoolean(config.getString("enable"));
		} catch (Exception e1) {
			enable=false;
		}
		log.debug("redis enable "+enable);
		if(enable){
			try {
				String tmp = config.getString("host");
				if(isNotNullOrEmpty(tmp)){
					host = tmp ;
				}
				log.debug(" redis host "+host);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				String tmp = config.getString("port");
				if(isNotNullOrEmpty(tmp)){
					port = Integer.parseInt(tmp);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				String tmp = config.getString("timeout");
				if(isNotNullOrEmpty(tmp)){
					timeout = Integer.parseInt(tmp);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				String tmp = config.getString("weight");
				if(isNotNullOrEmpty(tmp)){
					weight = Integer.parseInt(tmp);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public  static ShardedJedisPool getShardedJedisPool() {  
		if (pool == null) {  
			JedisPoolConfig config = new JedisPoolConfig();  
			//连接耗尽时是否阻塞, false报异常,ture阻塞直到超时, 默认true
			config.setBlockWhenExhausted(true);
			//设置的逐出策略类名, 默认DefaultEvictionPolicy(当连接超过最大空闲时间,或连接数超过最大空闲连接数)
			config.setEvictionPolicyClassName("org.apache.commons.pool2.impl.DefaultEvictionPolicy");
			//是否启用pool的jmx管理功能, 默认true
			config.setJmxEnabled(true);
			//MBean ObjectName = new ObjectName("org.apache.commons.pool2:type=GenericObjectPool,name=" + "pool" + i); 默认为"pool", JMX不熟,具体不知道是干啥的...默认就好.
			config.setJmxNamePrefix("pool");
			//是否启用后进先出, 默认true
			config.setLifo(true);
			//最大空闲连接数, 默认8个
			config.setMaxIdle(50);
			//最大连接数, 默认8个
			config.setMaxTotal(20000);
			//获取连接时的最大等待毫秒数(如果设置为阻塞时BlockWhenExhausted),如果超时就抛异常, 小于零:阻塞不确定的时间,  默认-1
			config.setMaxWaitMillis(300000);
			//逐出连接的最小空闲时间 默认1800000毫秒(30分钟)
			config.setMinEvictableIdleTimeMillis(1800000);
			//最小空闲连接数, 默认0
			config.setMinIdle(200);
			//每次逐出检查时 逐出的最大数目 如果为负数就是 : 1/abs(n), 默认3
			config.setNumTestsPerEvictionRun(3);
			//对象空闲多久后逐出, 当空闲时间>该值 且 空闲连接>最大空闲数 时直接逐出,不再根据MinEvictableIdleTimeMillis判断  (默认逐出策略)   
			config.setSoftMinEvictableIdleTimeMillis(1800000);
			//在获取连接的时候检查有效性, 默认false
			config.setTestOnBorrow(false);
			//在空闲时检查有效性, 默认false
			config.setTestWhileIdle(false);
			//逐出扫描的时间间隔(毫秒) 如果为负数,则不运行逐出线程, 默认-1
			config.setTimeBetweenEvictionRunsMillis(-1);
			//            pool = new JedisPool(config, "127.0.0.1", 6379); 
			List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
			JedisShardInfo info =new JedisShardInfo(host, port, timeout,weight);
			shards.add(info);
			pool = new ShardedJedisPool(config, shards);
		}
		return pool;
	}  
	public static void returnResource( ShardedJedis jedis) {  
		if (jedis != null) {  
			//			getShardedJedisPool().returnResourceObject(jedis);;
			jedis.close();
		}  
	}
	public static boolean exists(String jKey)throws Exception{
		ShardedJedis jedis = null;
		try {
			jedis =getShardedJedisPool().getResource();
			return jedis.exists(jKey);
		} catch (Exception e) {
			throw e;
		}finally{
			returnResource(jedis);
		}
	}
	public static boolean existsMapKey(String jKey,String key)throws Exception{
		ShardedJedis jedis = null;
		try {
			jedis =getShardedJedisPool().getResource();
			return jedis.hexists(jKey, key);
		} catch (Exception e) {
			throw e;
		}finally{
			returnResource(jedis);
		}
	}
	public static void setString(String jKey,String val)throws Exception{
		setString(jKey,val,null);
	}
	public static void setString(String jKey,String val,Integer seconds)throws Exception{
		ShardedJedis jedis = null;
		try {
			jedis =getShardedJedisPool().getResource();
			jedis.set(jKey, val);
			if(seconds!=null){
				jedis.expire(jKey,seconds);
			}
		} catch (Exception e) {
			throw e;
		}finally{
			returnResource(jedis);
		}
	}
	public static String getString(String jKey)throws Exception{
		ShardedJedis jedis = null;
		try {
			jedis =getShardedJedisPool().getResource();
			return jedis.get(jKey);
		} catch (Exception e) {
			throw e;
		}finally{
			returnResource(jedis);
		}
	}
	public static void setMap(String jKey,Map<? extends Object,? extends Object> map)throws Exception{
		setMap(jKey,map,null);
	}
	public static void setMap(String jKey,Map<? extends Object,? extends Object> map,Integer seconds)throws Exception{
		if(map==null){
			return ;
		}
		ShardedJedis jedis = null;
		try {
			Map<byte[], byte[]> tmp = convertMap2Byte(map);
			jedis =getShardedJedisPool().getResource();
			jedis.hmset(jKey.getBytes(), tmp);
			if(seconds!=null){
				jedis.expire(jKey,seconds);
			}
		} catch (Exception e) {
			throw e;
		}finally{
			returnResource(jedis);
		}
	}
	public static Map<? extends Object,? extends Object> getMap(String jKey)throws Exception{
		ShardedJedis jedis = null;
		try {
			jedis =getShardedJedisPool().getResource();
			Map<byte[], byte[]> tmp = jedis.hgetAll(jKey.getBytes());
			if(tmp==null){
				return null;
			}
			Map<? extends Object,? extends Object> map = convertByte2Map(tmp);
			return map;
		} catch (Exception e) {
			throw e;
		}finally{
			returnResource(jedis);
		}
	}
	public static long getMapSize(String jKey)throws Exception{
		ShardedJedis jedis = null;
		try {
			jedis =getShardedJedisPool().getResource();
			return jedis.hlen(jKey);
		} catch (Exception e) {
			throw e;
		}finally{
			returnResource(jedis);
		}
	}
	public static long getListSize(String jKey)throws Exception{
		ShardedJedis jedis = null;
		try {
			jedis =getShardedJedisPool().getResource();
			return jedis.llen(jKey);
		} catch (Exception e) {
			throw e;
		}finally{
			returnResource(jedis);
		}
	}
	public static void appendMap(String jKey,Map<Object,Object> map)throws Exception{
		if(map==null||map.isEmpty()){
			return ;
		}
		ShardedJedis jedis = null;
		try {
			byte[] bkey = jKey.getBytes();
			jedis =getShardedJedisPool().getResource();
			Map<byte[],byte[]> tmp  = convertMap2Byte(map);
			Set<byte[]> keys =tmp.keySet();
			for (Iterator<byte[]> iterator = keys.iterator(); iterator.hasNext();) {
				byte[] key = (byte[]) iterator.next();
				jedis.hset(bkey, key, tmp.get(key));
			}
		} catch (Exception e) {
			throw e;
		}finally{
			returnResource(jedis);
		}
	}
	public static void setMapVal(String jKey,Object key,Object val)throws Exception{
		ShardedJedis jedis = null;
		try {
			jedis =getShardedJedisPool().getResource();
			jedis.hset(jKey.getBytes(), convertObject2Byte(key), convertObject2Byte(val));
		} catch (Exception e) {
			throw e;
		}finally{
			returnResource(jedis);
		}
	}
	public static Set<Object> getMapKeySet(String jKey)throws Exception{
		ShardedJedis jedis = null;
		try {
			jedis =getShardedJedisPool().getResource();
			Set<byte[]> tmp = jedis.hkeys(jKey.getBytes());
			if(tmp ==null){
				return null;
			}
			Set<Object> set = new HashSet<Object>();
			for (Iterator<byte[]> iterator = tmp.iterator(); iterator.hasNext();) {
				byte[] bs = (byte[]) iterator.next();
				set.add(convertByte2Object(bs));
			}
			tmp.clear();
			return set;
		} catch (Exception e) {
			throw e;
		}finally{
			returnResource(jedis);
		}
	}
	public static List<Object> getMapValList(String jKey)throws Exception{
		ShardedJedis jedis = null;
		try {
			jedis =getShardedJedisPool().getResource();
			Collection<byte[]> tmp = jedis.hvals(jKey.getBytes());
			if(tmp ==null){
				return null;
			}
			List<Object> list = new ArrayList<Object>();
			for (Iterator<byte[]> iterator = tmp.iterator(); iterator.hasNext();) {
				byte[] bs = (byte[]) iterator.next();
				list.add(convertByte2Object(bs));
			}
			tmp.clear();
			return list;
		} catch (Exception e) {
			throw e;
		}finally{
			returnResource(jedis);
		}
	}
	public static Object getMapVal(String jKey,Object key)throws Exception{
		List<Object> list = getMapVal(jKey,new Object[]{key});
		if(list==null){
			return null;
		}
		return list.get(0);
	}
	public static List<Object> getMapVal(String jKey,Object... key)throws Exception{
		ShardedJedis jedis = null;
		try {
			if(key==null){
				return null;
			}
			byte[][] keys = new byte[key.length][];
			for (int i = 0; i < key.length; i++) {
				keys[i] = convertObject2Byte(key[i]);
			}
			jedis =getShardedJedisPool().getResource();
			Collection<byte[]> tmp = jedis.hmget(jKey.getBytes(), keys);
			if(tmp ==null){
				return null;
			}
			List<Object> list = new ArrayList<Object>();
			for (Iterator<byte[]> iterator = tmp.iterator(); iterator.hasNext();) {
				byte[] bs = (byte[]) iterator.next();
				list.add(convertByte2Object(bs));
			}
			tmp.clear();
			return list;
		} catch (Exception e) {
			throw e;
		}finally{
			returnResource(jedis);
		}
	}
	public static void delMapKey(String jKey,Object... key)throws Exception{
		ShardedJedis jedis = null;
		try {
			jedis =getShardedJedisPool().getResource();
			jedis.hdel(jKey.getBytes(), convertList2Byte(key));
		} catch (Exception e) {
			throw e;
		}finally{
			returnResource(jedis);
		}
	}
	public static void delMapKey(String jKey,Object key)throws Exception{
		delMapKey (jKey,new Object[]{key});
	}
	public static void setList(String jKey,List<?> list) throws Exception{
		setList(jKey,list,null);
	}
	public static void setList(String jKey,List<?> list,Integer seconds) throws Exception{
		if(list==null||list.size()==0){
			return ;
		}
		ShardedJedis jedis = null;
		try {
			byte[][] bytes = convertList2Byte(list);
			jedis =getShardedJedisPool().getResource();
			jedis.del(jKey);
			jedis.rpush(jKey.getBytes(), bytes);
			if(seconds!=null){
				jedis.expire(jKey,seconds);
			}
		} catch (Exception e) {
			throw e;
		}finally{
			returnResource(jedis);
		}
	}
	public static void appendList(String jKey,List<Object> list) throws Exception{
		if(list==null||list.size()==0){
			return ;
		}
		ShardedJedis jedis = null;
		try {
			byte[][] bytes = new byte[list.size()][] ;
			int i=0;
			for (Iterator<Object> iterator = list.iterator(); iterator.hasNext();) {
				Object object =  iterator.next();
				bytes[i] = convertObject2Byte(object);
				i++;
			}
			jedis =getShardedJedisPool().getResource();
			jedis.rpush(jKey.getBytes(), bytes);
		} catch (Exception e) {
			throw e;
		}finally{
			returnResource(jedis);
		}
	}
	public static void appendList(String jKey,Object obj) throws Exception{
		ShardedJedis jedis = null;
		try {
			jedis =getShardedJedisPool().getResource();
			jedis.rpush(jKey.getBytes(), convertObject2Byte(obj));
		} catch (Exception e) {
			throw e;
		}finally{
			returnResource(jedis);
		}
	}
	public static List<?> getList(String jKey) throws Exception{
		return getList(jKey,0,-1);
	} 
	public static Object getList(String jKey,int index) throws Exception{
		ShardedJedis jedis = null;
		try {

			jedis =getShardedJedisPool().getResource();
			byte[] by =jedis.lindex(jKey.getBytes(), index);
			return convertByte2Object(by) ;
		} catch (Exception e) {
			throw e;
		}finally{
			returnResource(jedis);
		}
	} 
	public static List<?> getList(String jKey,int begin,int end) throws Exception{
		ShardedJedis jedis = null;
		try {

			jedis =getShardedJedisPool().getResource();
			List<byte[]> list =jedis.lrange(jKey.getBytes(), begin, end);
			int i =0;
			for (Iterator<byte[]> iterator = list.iterator(); iterator.hasNext();) {
				byte[] object = (byte[]) iterator.next();
				list.set(i, (byte[])convertByte2Object(object));
				i++;
			}
			return list ;
		} catch (Exception e) {
			throw e;
		}finally{
			returnResource(jedis);
		}
	}
	public static void del(String jKey)throws Exception{
		ShardedJedis jedis = null;
		try {
			jedis =getShardedJedisPool().getResource();
			jedis.del(jKey);
		} catch (Exception e) {
			throw e;
		}finally{
			returnResource(jedis);
		}
	}
	public static void setObject(String jKey,Object obj)throws Exception{
		setObject(jKey,obj,null);
	}
	public static void setObject(String jKey,Object obj,Integer seconds)throws Exception{
		if(obj==null){
			return ;
		}
		if(!(obj instanceof Serializable)){
			throw new Exception(obj.getClass().toString()+"没有实现Serializable");
		}
		ShardedJedis jedis = null;
		try {
			jedis =getShardedJedisPool().getResource();
			jedis.set(jKey.getBytes(), convertObject2Byte(obj));
			if(seconds!=null){
				jedis.expire(jKey,seconds);
			}
		} catch (Exception e) {
			throw e;
		}finally{
			returnResource(jedis);
		}
	}
	public static Object getObject(String jKey)throws Exception{
		ShardedJedis jedis = null;
		try {
			jedis =getShardedJedisPool().getResource();
			Object obj =   convertByte2Object(jedis.get(jKey.getBytes()));
			return obj;
		} catch (Exception e) {
			throw e;
		}finally{
			returnResource(jedis);
		}
	}
	private static byte[] convertObject2Byte(Object obj) throws Exception{
		if(obj==null){
			return null;
		}
		ByteArrayOutputStream bos=null;
		ObjectOutputStream oos =null;
		try {
			bos =  new ByteArrayOutputStream();
			oos =  new ObjectOutputStream(bos);
			oos.writeObject(obj);
			return bos.toByteArray();
		} catch (Exception e) {
			throw e;
		} finally{
			if(oos!=null){
				oos.close();
			}
			if(bos!=null){
				bos.close();
			}
		}
	}
	private static Object convertByte2Object( byte[] bs) throws Exception{
		if(bs==null){
			return null;
		}
		ByteArrayInputStream bis=null;
		ObjectInputStream ois =null;
		try {
			bis =  new ByteArrayInputStream(bs);
			ois =  new ObjectInputStream(bis); 
			return ois.readObject();
		} catch (Exception e) {
			throw e;
		} finally{
			if(bis!=null){
				bis.close();
			}
			if(ois!=null){
				ois.close();
			}
		}
	}
	private static Map<byte[], byte[]> convertMap2Byte(Map<? extends Object,? extends Object> map)throws Exception {
		if(map ==null){
			return null;
		}
		Set<? extends Object> set = map.keySet();
		Map<byte[], byte[]> res = new HashMap<byte[], byte[]>();
		for (Iterator<?> iterator = set.iterator(); iterator.hasNext();) {
			Object object = (Object) iterator.next();
			res.put(convertObject2Byte(object),convertObject2Byte(map.get(object)));
		}
		//map.clear();
		return res;
	}
	private static Map<? extends Object,? extends Object> convertByte2Map(Map<byte[], byte[]> map)throws Exception {
		if(map ==null){
			return null;
		}
		Set<byte[]> set = map.keySet();
		Map<Object,Object> res = new HashMap<Object,Object>();
		for (Iterator<?> iterator = set.iterator(); iterator.hasNext();) {
			byte[] object = (byte[]) iterator.next();
			res.put(convertByte2Object(object),convertByte2Object(map.get(object)));
		}
		//map.clear();
		return res;
	}
	private static byte[][] convertList2Byte(List<Object> list) throws Exception{
		if(list==null){
			return null;
		}
		byte[][] bytes = new byte[list.size()][] ;
		int i=0;
		for (Iterator<Object> iterator = (Iterator<Object>) list.iterator(); iterator.hasNext();) {
			Object object =  iterator.next();
			bytes[i] = convertObject2Byte(object);
			i++;
		}
		return bytes;
	}
	private static byte[][] convertList2Byte(Object... objects ) throws Exception{
		if(objects==null){
			return null;
		}
		byte[][] bytes = new byte[objects.length][] ;
		int index=objects.length;
		for (int j = 0; j < index; j++) {
			bytes[j] = convertObject2Byte(objects[j]);
		}
		return bytes;
	}
	@SuppressWarnings({  "unused" })
	private static List<Object> convertByte2List(byte[][] bs) throws Exception{
		if(bs==null){
			return null;
		}
		List<Object> list = new ArrayList<Object>();
		int len =bs.length;
		for (int i = 0; i <len ; i++) {
			list.add(convertByte2Object(bs[i]));
		}
		return list;
	}
	private static boolean isNotNullOrEmpty(String test){
		if(test==null||"".equals(test.trim())){
			return false;
		}
		return true;
	}
	/*public static void main(String[] args) throws Exception {
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		Map<String,String> map = new HashMap<String, String>();
		map.put("t112", "v1123");
		map.put("t212", "测试");
//				list.add(map);
//				Map<String,String> temp = new HashMap<String, String>();
//				temp.put("t001", "t001");
//				temp.put("t002", "t002");
//				list.add(temp);
//				JedisTool.setList("list", list);
//				System.out.println(JedisTool.getList("list",0));
//				JedisTool.setMap("test", map);
		System.out.println(JedisTool.getMapVal("test", "t112"));
	}*/
}  