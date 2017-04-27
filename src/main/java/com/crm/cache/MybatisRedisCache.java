package com.crm.cache;

import org.apache.ibatis.cache.decorators.LoggingCache;



public class MybatisRedisCache extends LoggingCache{

	public MybatisRedisCache(String id) {
		super(new RedisCache(id));
	}
	
}
