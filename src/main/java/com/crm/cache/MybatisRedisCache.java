package com.crm.cache;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.ibatis.cache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MybatisRedisCache implements Cache{
	private static Logger logger = LoggerFactory.getLogger(MybatisRedisCache.class);
	/**
	 * The ReadWriteLock
	 */
	private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
	
	private String id;
	
	public MybatisRedisCache(final String id) throws IllegalAccessException {
		if(id == null){
			throw new IllegalAccessException("Cache instances require an ID");
		}
		logger.debug(">>>>>>>>>>>>>>>>>>>>MybatisRedisCache:id="+id);
		this.id = id;
	}

	public void clear() {
		
	}

	public String getId() {
		return this.id;
	}

	public Object getObject(Object arg0) {
		return null;
	}

	public ReadWriteLock getReadWriteLock() {
		return null;
	}

	public int getSize() {
		return 0;
	}

	public void putObject(Object key, Object value) {
		logger.debug(">>>>>>>>>>>>>>>>>>>>putObject:"+key+"="+value);
	}

	public Object removeObject(Object arg0) {
		return null;
	}
	
}
