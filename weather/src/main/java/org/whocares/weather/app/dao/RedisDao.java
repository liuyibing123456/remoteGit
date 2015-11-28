package org.whocares.weather.app.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

public class RedisDao {
	
	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	
	public final RedisTemplate<String, String> getRedisTemplate() {
		return this.redisTemplate;
	}
	
	public final void multi() {
		this.redisTemplate.multi();
	}
	
	public final void exec() {
		this.redisTemplate.exec();
	}

}
