package com.crm.cache;

import java.util.Collection;

import org.springframework.cache.Cache;
import org.springframework.cache.support.AbstractCacheManager;


/**
 * 
 * @ClassName CacheManager
 * @Description 继承了 spring 的 AbstractCacheManager 管理 RedisCache 类缓存管理
 * @author hack
 * @version 1.0.0
 * @param <T>
 */
public class CacheManage<T extends Object> extends AbstractCacheManager{
	private Collection<? extends RedisCache> caches;
	public void setCaches(Collection<? extends RedisCache> caches){
		this.caches = caches;
	}
	
	protected Collection<? extends Cache> loadCaches() {
		return (Collection<? extends Cache>) this.caches;
	}
}
