package com.jinhui.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import com.jinhui.constant.ConstantEntity;
import org.springframework.data.redis.core.RedisTemplate;

public class RedisUtils {

	private static ThreadLocal<String> TokenLocal = new ThreadLocal<>();
	
	/**
	 * 
	 * 
	 * 
	 * @param 
	 * @param 
	 * @param 
	 *            数据库
	 * @throws Exception
	 */
	public static void saveAttributeForHashDb(String key, Serializable obj,
			int dbnum) {
		RedisTemplate<Serializable, Serializable> redisTemplate = SpringContextHolder
				.getBean("redisTemplate");
		redisTemplate.opsForValue().set(key, obj);
	}

	/**
	 * 
	 * 
	 * 
	 * @param 
	 * @param 
	 *            
	 * @return
	 * @throws Exception
	 */
	public static Object getAttributeForHashDb(String key, int dbnum)
			throws Exception {
		// SpringContextHolder.getApplicationContext().getBean(JedisConnectionFactory.class).setDatabase(dbnum);
		RedisTemplate<Serializable, Serializable> redisTemplate = SpringContextHolder
				.getBean("redisTemplate");
		return redisTemplate.opsForValue().get(key);
	}

	/**
	 * 
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static Object getAttributeForHash(String key, Object hashKey)
			throws Exception {
		RedisTemplate<Serializable, Serializable> redisTemplate = SpringContextHolder
				.getBean("redisTemplate");
		return redisTemplate.opsForHash().get(key, hashKey);
	}

	/**
	 *
	 * 
	 * @param 
	 * @param 
	 * @return
	 * @throws Exception
	 */
	public static Object setAttributeForHash(String key, Object hashKey,
			Object value) throws Exception {
		RedisTemplate<Serializable, Serializable> redisTemplate = SpringContextHolder
				.getBean("redisTemplate");
		redisTemplate.opsForHash().put(key, hashKey, value);
		return value;
	}

	/**
	 * 
	 * 
	 * @param 
	 * @return
	 * @throws Exception
	 */
	public static boolean removeAttributeForHash(String key, Object hashKey)
			throws Exception {
		RedisTemplate<Serializable, Serializable> redisTemplate = SpringContextHolder
				.getBean("redisTemplate");
		try {
			redisTemplate.opsForHash().delete(key, hashKey);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 
	 * 
	 * @param 
	 * @return
	 * @throws Exception
	 */
	public static boolean removeAttributeForHash(String key) throws Exception {
		RedisTemplate<Serializable, Serializable> redisTemplate = SpringContextHolder
				.getBean("redisTemplate");
		try {
			Set<Object> objs = redisTemplate.opsForHash().keys(key);
			if (null == objs)
				return true;
			for (Object object : objs) {
				redisTemplate.opsForHash().delete(key, object);
			}

			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 
	 * 
	 * 
	 * @param 
	 *            
	 * @return
	 * @throws Exception
	 */
	public static Set<Object> getHashKeyListForHash(String key)
			throws Exception {
		RedisTemplate<Serializable, Serializable> redisTemplate = SpringContextHolder
				.getBean("redisTemplate");
		return redisTemplate.opsForHash().keys(key);
	}

	/**
	 *
	 * 
	 * @param 
	 *          
	 * @param 
	 *           
	 * @throws Exception
	 */
	public static void removeValue(String key, String groupId) throws Exception {
		RedisTemplate<Serializable, Serializable> redisTemplate = SpringContextHolder
				.getBean("redisTemplate");
		redisTemplate.delete(groupId + key);
	}

	/**
	 * @param
	 * @param
	 * @return
	 */
	public static Serializable getValue(String key, String groupId) {
		RedisTemplate<Serializable, Serializable> redisTemplate = SpringContextHolder
				.getBean("redisTemplate");
		return redisTemplate.opsForValue().get(groupId + key);
	}

	public static Serializable getValue(String key) {
		RedisTemplate<Serializable, Serializable> redisTemplate = SpringContextHolder
				.getBean("redisTemplate");
		return redisTemplate.opsForValue().get(key);
	}

	/**
	 * 
	 * 
	 * 
	 * @param 
	 *            
	 * @param 
	 *            
	 * @param 
	 *           
	 *           
	 * @throws Exception
	 */
	public static void setValue(String key, String groupId, Serializable value,
			long timeout) {
		RedisTemplate<Serializable, Serializable> redisTemplate = SpringContextHolder
				.getBean("redisTemplate");
		redisTemplate.opsForValue().set(groupId + key, value, timeout,
				TimeUnit.MINUTES);
	}

	/**
	 * 刷新用户访问请求最近访问时间
	 * {@link org.apache.catalina.Session#access()}
	 */
	public static void touch(String key, String groupId, Serializable value,
							 long timeout) {
		TokenLocal.set(key);
		setValue(key, groupId, value, timeout);
	}

	/**
	 * 从安全角度看，用户敏感信息，是从当前会话中，返回成功登录的真实用户。
	 * @return
	 */
	public static String getAuthenticUser(){
		try {
			return (String) getValue(TokenLocal.get(), ConstantEntity.USER_TICKET);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 
	 * 
	 * @param 
	 * @param 
	 * @param
	 */
	public static void setValue(String key, String groupId, Serializable value) {
		RedisTemplate<Serializable, Serializable> redisTemplate = SpringContextHolder
				.getBean("redisTemplate");
		redisTemplate.opsForValue().set(groupId + key, value);
	}

	public static void setValue(String key, Serializable value,
								long timeout, TimeUnit timeUnit) {
		RedisTemplate<Serializable, Serializable> redisTemplate = SpringContextHolder
				.getBean("redisTemplate");
		redisTemplate.opsForValue().set(key, value, timeout, timeUnit);
	}

	/**
	 * 
	 * 
	 * 
	 * @param 
	 * @return
	 * @throws Exception
	 */
	public static List<Serializable> getValues(String groupId) throws Exception {
		RedisTemplate<Serializable, Serializable> redisTemplate = SpringContextHolder
				.getBean("redisTemplate");
		List<Serializable> serializables = new ArrayList<Serializable>();
		Set<Serializable> keys = (Set<Serializable>) redisTemplate.keys(groupId
				+ "*");
		if (keys == null || keys.isEmpty()) {
			return null;
		}
		Iterator<Serializable> iterator = keys.iterator();
		while (iterator.hasNext()) {
			serializables.add(redisTemplate.opsForValue().get(iterator.next()));
		}
		return serializables;
	}
}
