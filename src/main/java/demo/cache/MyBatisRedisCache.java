package demo.cache;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.ibatis.cache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;

import demo.utils.RedisUtils;
import demo.utils.SerializeUtil;
import demo.utils.SpringUtils;

public class MyBatisRedisCache implements Cache{
	private static Logger logger = LoggerFactory.getLogger(MyBatisRedisCache.class);
	private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
	
	private String id;
	
	private RedisUtils redisUtils;

	public MyBatisRedisCache(final String id) {
		if(id == null) {
			throw new IllegalArgumentException("cache instances require an ID");
		}

		/*if(redisUtils == null) {
			logger.debug("afdafdsalfkjas;fjas;fjdasfdsafdsda");
			redisUtils = new RedisUtils();
		}*/
		logger.debug(">>>>>>>>>>>>>>>>Mybatis Redis cache: id = {}", id);
		this.id = id;
	}

	@Override
	public void clear() {
		Jedis jedis = null;
		try {
			redisUtils = (RedisUtils) SpringUtils.getObject("redisUtils");
			jedis = redisUtils.getJedis();
			jedis.flushDB();
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			if(jedis != null) {
				redisUtils.returnResource(jedis);
			}
		}
		
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public Object getObject(Object key) {
		Jedis jedis = null;
		Object value = null;
		try {
			redisUtils = (RedisUtils) SpringUtils.getObject("redisUtils");
			jedis = redisUtils.getJedis();
			value = SerializeUtil.unserialize(jedis.get(SerializeUtil.serialize(key.toString())));
			logger.debug(">>>>>>>>>>get object: key: {}, value: {}", key, value);
		} catch (Exception e) {
			logger.error("fail to get object", e);
		} finally {
			if(jedis != null) {
				redisUtils.returnResource(jedis);
			}
		}
		return value;
	}

	@Override
	public ReadWriteLock getReadWriteLock() {
		return readWriteLock;
	}

	@Override
	public int getSize() {
		Jedis jedis = null;
		int size = 0;
		try {
			redisUtils = (RedisUtils) SpringUtils.getObject("redisUtils");
			jedis = redisUtils.getJedis();
			size = Integer.valueOf(jedis.dbSize().toString());
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			if(jedis != null) {
				redisUtils.returnResource(jedis);
			}
		}
		return size;
	}

	@Override
	public void putObject(Object key, Object value) {
		logger.debug(">>>>>>>>put object: key: {}, value: {}", key, value);
		Jedis jedis = null;
		try {
			redisUtils = (RedisUtils) SpringUtils.getObject("redisUtils");
			jedis = redisUtils.getJedis();
			jedis.set(SerializeUtil.serialize(key.toString()), SerializeUtil.serialize(value));
		} catch (Exception e) {
			logger.error("fail to put object", e);
		} finally {
			if(jedis != null) {
				redisUtils.returnResource(jedis);
			}
		}
	}

	@Override
	public Object removeObject(Object key) {
		Jedis jedis = null;
		Object value = null;
		try {
			redisUtils = (RedisUtils) SpringUtils.getObject("redisUtils");
			jedis = redisUtils.getJedis();
			value = jedis.expire(SerializeUtil.serialize(key.toString()), 0);
			logger.debug(">>>>>>>>>>get object: key: {}, value: {}", key, value);
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			if(jedis != null) {
				redisUtils.returnResource(jedis);
			}
		}
		return value;
	}
	
}
