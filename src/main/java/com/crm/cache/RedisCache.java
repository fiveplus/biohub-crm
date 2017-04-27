package com.crm.cache;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.cache.Cache;

import com.crm.utils.SerializeUtils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisConnectionException;
/**
 * @ClassName RedisCache
 * @Description 接口实现 redis 功能类
 * @author hack
 *
 */
public class RedisCache implements Cache{
	private static Log log = LogFactory.getLog(RedisCache.class);
	private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
	
	private String id;
	
	public RedisCache(final String id){
		if(id == null){
			throw new IllegalArgumentException("必须传入ID");
		}
		log.debug("MybatisRedisCache:id=" + id);
		this.id = id;
	}
	
	public String getId() {
		return this.id;
	}
	
	public int getSize() {
		Jedis jedis = null;
		JedisPool jedisPool = null;
		int result = 0;
		boolean borrowOrOprSuccess = true;
		try {
			jedis = CachePool.getInstance().getJedis();
			jedisPool = CachePool.getInstance().getJedisPool();
			result = Integer.valueOf(jedis.dbSize().toString());
		} catch (JedisConnectionException e) {
			borrowOrOprSuccess = false;
			if (jedis != null)
				jedisPool.returnBrokenResource(jedis);
		} finally {
			if (borrowOrOprSuccess)
				jedisPool.returnResource(jedis);
		}
		return result;
	}
	
	public void putObject(Object key, Object value) {
		if (log.isDebugEnabled())
			log.debug("putObject:" + key.hashCode() + "=" + value);
		if (log.isInfoEnabled())
			log.info("put to redis sql :" + key.toString());
		Jedis jedis = null;
		JedisPool jedisPool = null;
		boolean borrowOrOprSuccess = true;
		try {
			jedis = CachePool.getInstance().getJedis();
			jedisPool = CachePool.getInstance().getJedisPool();
			jedis.set(SerializeUtils.serialize(key.hashCode()), SerializeUtils.serialize(value));
		} catch (JedisConnectionException e) {
			borrowOrOprSuccess = false;
			if (jedis != null)
				jedisPool.returnBrokenResource(jedis);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (borrowOrOprSuccess)
				jedisPool.returnResource(jedis);
		}

	}
	
	public Object getObject(Object key) {
		Jedis jedis = null;
		JedisPool jedisPool = null;
		Object value = null;
		boolean borrowOrOprSuccess = true;
		try {
			jedis = CachePool.getInstance().getJedis();
			jedisPool = CachePool.getInstance().getJedisPool();
			System.out.println("key's hashcode:"+key.hashCode());
			System.out.println("key's hashcode serialize:"+SerializeUtils.serialize(key.hashCode()));
			System.out.println("value's serialize:"+jedis.get(SerializeUtils.serialize(key.hashCode())));
			value = SerializeUtils.unSerialize(jedis.get(SerializeUtils.serialize(key.hashCode())));
		} catch (JedisConnectionException e) {
			borrowOrOprSuccess = false;
			if (jedis != null)
				jedisPool.returnBrokenResource(jedis);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (borrowOrOprSuccess)
				jedisPool.returnResource(jedis);
		}
		if (log.isDebugEnabled())
			log.debug("getObject:" + key.hashCode() + "=" + value);
		return value;
	}
	
	public Object removeObject(Object key) {
		Jedis jedis = null;
		JedisPool jedisPool = null;
		Object value = null;
		boolean borrowOrOprSuccess = true;
		try {
			jedis = CachePool.getInstance().getJedis();
			jedisPool = CachePool.getInstance().getJedisPool();
			value = jedis.expire(SerializeUtils.serialize(key.hashCode()), 0);
		} catch (JedisConnectionException e) {
			borrowOrOprSuccess = false;
			if (jedis != null)
				jedisPool.returnBrokenResource(jedis);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (borrowOrOprSuccess)
				jedisPool.returnResource(jedis);
		}
		if (log.isDebugEnabled())
			log.debug("getObject:" + key.hashCode() + "=" + value);
		return value;
	}
	
	public void clear() {
		Jedis jedis = null;
		JedisPool jedisPool = null;
		boolean borrowOrOprSuccess = true;
		try {
			jedis = CachePool.getInstance().getJedis();
			jedisPool = CachePool.getInstance().getJedisPool();
			jedis.flushDB();
			jedis.flushAll();
		} catch (JedisConnectionException e) {
			borrowOrOprSuccess = false;
			if (jedis != null)
				jedisPool.returnBrokenResource(jedis);
		} finally {
			if (borrowOrOprSuccess)
				jedisPool.returnResource(jedis);
		}
	}
	
	public ReadWriteLock getReadWriteLock() {
		return readWriteLock;
	}
	
}
