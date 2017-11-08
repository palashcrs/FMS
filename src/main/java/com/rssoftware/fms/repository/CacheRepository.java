package com.rssoftware.fms.repository;

public interface CacheRepository {

	void save(String key, Object obj);

	Object findByKey(String key);
}
