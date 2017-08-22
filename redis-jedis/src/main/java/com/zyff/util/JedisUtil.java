package com.zyff.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.zyff.config.RedisConfig;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * jedis工具类
 * @author liangz
 *
 */
public class JedisUtil {
	private Logger logger = Logger.getLogger(this.getClass());
	
	private JedisUtil() {}
	
	private static class RedisUtilHolder {
		private static final JedisUtil instance = new JedisUtil();
	}
	
	public static JedisUtil getInstance() {
		return RedisUtilHolder.instance;
	}
	
	private static Map<String, JedisPool> maps = new HashMap<String, JedisPool>();
	
	/**
	 * 构建连接池
	 * @param ip
	 * @param port
	 * @return
	 */
	private static JedisPool getPool(String ip, int port) {
		String key = ip + ":" + port;
		JedisPool pool = null;
		if (!maps.containsKey(key)) {
			JedisPoolConfig config = new JedisPoolConfig();
			config.setMaxTotal(RedisConfig.MAX_TOTAL);
			config.setMaxIdle(RedisConfig.MAX_IDLE);
			config.setMaxWaitMillis(RedisConfig.MAX_WAIT);
			config.setTestOnBorrow(true);
			config.setTestOnReturn(true);
			
			pool = new JedisPool(config, ip, port, RedisConfig.TIMEOUT);
			maps.put(key, pool);
		} else {
			pool = maps.get(key);
		}
		return pool;
	}
	
	/**
	 * 获取jedis连接
	 * @param ip
	 * @param port
	 * @return
	 */
	public Jedis getJedis(String ip, int port) {
		Jedis jedis = null;
		int count = 0;
		do {
			try {
				jedis = getPool(ip, port).getResource();
			} catch (Exception e) {
				logger.error("get redis master failed!", e);
				if (jedis != null) {
					jedis.close();
				}
			}
		} while (jedis == null && count < RedisConfig.RETRY_NUM);
		return jedis;
	}
	
	/**
	 * 关闭jedis
	 * @param jedis
	 */
	public void closeJedis(Jedis jedis) {
		if (jedis != null) {
			jedis.close();
		}
	}
}
