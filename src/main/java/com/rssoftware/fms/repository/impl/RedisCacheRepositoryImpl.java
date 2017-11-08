package com.rssoftware.fms.repository.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.rssoftware.fms.repository.CacheRepository;

@Repository
public class RedisCacheRepositoryImpl implements CacheRepository {

	private static final Logger log = LoggerFactory.getLogger(RedisCacheRepositoryImpl.class);

	@Autowired
	private RedisTemplate<Object, Object> redisTemplate;

	@Override
	public void save(String key, Object obj) {
		redisTemplate.opsForValue().set(key, obj);
	}

	@Override
	public Object findByKey(String key) {
		return redisTemplate.opsForValue().get(key);
	}

}
