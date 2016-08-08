package demo.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisUtils {
	private static JedisPool jedisPool;
	
	public static Jedis getJedis() {
		if(jedisPool == null) {
			jedisPool = new JedisPool(new JedisPoolConfig(), "localhost");
		}
		return jedisPool.getResource();
	}
}
