package demo.cache;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.ibatis.cache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import demo.utils.RedisUtils;
import demo.utils.SerializeUtil;

public class MyBatisRedisCache implements Cache{
	private static Logger logger = LoggerFactory.getLogger(MyBatisRedisCache.class);
	private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
	
	private String id;
	
	public MyBatisRedisCache(final String id) {
		if(id == null) {
			throw new IllegalArgumentException("cache instances require an ID");
		}
		logger.debug(">>>>>>>>>>>>>>>>Mybatis Redis cache: id = {}", id);
		this.id = id;
	}

	@Override
	public void clear() {
		RedisUtils.getJedis().flushDB();
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public Object getObject(Object key) {
		Object value = SerializeUtil.unserialize(RedisUtils.getJedis().get(SerializeUtil.serialize(key.toString())));
		logger.debug(">>>>>>>>>>get object: key: {}, value: {}", key, value);
		return value;
	}

	@Override
	public ReadWriteLock getReadWriteLock() {
		return readWriteLock;
	}

	@Override
	public int getSize() {
		return Integer.valueOf(RedisUtils.getJedis().dbSize().toString());
	}

	@Override
	public void putObject(Object key, Object value) {
		logger.debug(">>>>>>>>put object: key: {}, value: {}", key, value);
		RedisUtils.getJedis().set(SerializeUtil.serialize(key.toString()), SerializeUtil.serialize(value));
	}

	@Override
	public Object removeObject(Object key) {
		return RedisUtils.getJedis().expire(SerializeUtil.serialize(key.toString()), 0);
	}
	
}
