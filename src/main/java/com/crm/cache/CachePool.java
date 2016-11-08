package com.crm.cache;

import com.crm.utils.RedisUtils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisConnectionException;

public class CachePool {
	JedisPool pool;
	private static final CachePool cachePool = new CachePool();
	/**
	 * 单例模式
	 */
	public static CachePool getInstance(){
		return cachePool;
	}
	
	/**
	 * 初始化
	 */
	private CachePool(){
		pool = RedisUtils.getjedisPool();
	}
	
	public Jedis getJedis(){
		Jedis jedis = null;
		boolean borrowOrOprSuccess = true;
		try{
			jedis = pool.getResource();
		}catch(JedisConnectionException e){
			borrowOrOprSuccess = false;
			if(jedis != null){
				pool.returnBrokenResource(jedis);
			}
		}finally{
			if(borrowOrOprSuccess){
				pool.returnResource(jedis);
			}
		}
		jedis = pool.getResource();
		return jedis;
	}
	
	public JedisPool getJedisPool(){
		return this.pool;
	}
}
