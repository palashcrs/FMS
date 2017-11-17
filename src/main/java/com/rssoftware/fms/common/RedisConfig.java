package com.rssoftware.fms.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Configuration
@Component
public class RedisConfig {

	private static final Logger log = LoggerFactory.getLogger(RedisConfig.class);

	@Bean
	public JedisConnectionFactory jedisConnectionFactory() {
		JedisConnectionFactory jedisConFactory = new JedisConnectionFactory();
		jedisConFactory.setHostName("127.0.0.1");
		jedisConFactory.setPort(6379);
		jedisConFactory.setTimeout(3000);
		jedisConFactory.setDatabase(0);

		return jedisConFactory;
	}

	@Bean
	public RedisTemplate<Object, Object> redisTemplate() {
		RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<Object, Object>();
		redisTemplate.setConnectionFactory(jedisConnectionFactory());
		return redisTemplate;
	}

	@Bean
	public RedisConnection redisCon() {
		RedisConnection redisCon = null;
		try {
			redisCon = jedisConnectionFactory().getConnection();
		} catch (RedisConnectionFailureException e) {
			log.info("Failed to start Tomcat. Please start Redis server first!...");
			System.exit(1);
		}

		return redisCon;
	}

}
