package com.zyff;

import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisTest {
	
	@Test
	public void test() {
		Jedis jedis = new Jedis("127.0.0.1");
		jedis.set("foo", "bar");
		String value = jedis.get("foo");
		Assert.assertEquals("bar", value);
	}
	
	@Test
	public void test1() {
		JedisPool pool = new JedisPool(new JedisPoolConfig(), "127.0.0.1");
		// Jedis实现了Closeable接口。因此，jedis实例将在最后语句之后自动关闭。
		try (Jedis jedis = pool.getResource()) {
			jedis.set("foo", "bar");
			String foobar = jedis.get("foo");
			jedis.zadd("sose", 0, "car");
			jedis.zadd("sose", 0, "bike");
			Set<String> sose = jedis.zrange("sose", 0, -1);
			Assert.assertEquals(2, sose.size());
		}
		
		//执行完操作后销毁连接池
		pool.destroy();
	}
	
	@Test
	public void test2() {
		JedisPool pool = new JedisPool(new JedisPoolConfig(), "127.0.0.1");
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			jedis.flushDB();
			jedis.set("foo", "bar");
			String value = jedis.get("foo");
			Assert.assertEquals("bar", value);
			jedis.zadd("sose", 0, "car");
			jedis.zadd("sose", 0, "bike");
			Set<String> sose = jedis.zrange("sose", 0, -1);
			Assert.assertEquals(2, sose.size());
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		pool.destroy();
	}
	
	@Test
	public void test3() {
		JedisPool pool = new JedisPool(new JedisPoolConfig(), "127.0.0.1");
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			jedis.flushDB();
			jedis.set("foo", "bar");
			String value = jedis.get("foo");
			Assert.assertEquals("bar", value);
			jedis.zadd("sose", 0, "car");
			jedis.zadd("sose", 0, "bike");
			Set<String> sose = jedis.zrange("sose", 0, -1);
			Assert.assertEquals(2, sose.size());
			
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		pool.destroy();
	}
}
