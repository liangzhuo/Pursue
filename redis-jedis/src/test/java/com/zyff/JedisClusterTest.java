package com.zyff;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

public class JedisClusterTest {
	
	@Test
	public void test() {
		Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
		jedisClusterNodes.add(new HostAndPort("192.168.0.227", 6379));
		JedisCluster jc = new JedisCluster(jedisClusterNodes);
		jc.set("foo_cluser", "bar_cluser");
		String value = jc.get("foo_cluser");
		Assert.assertEquals("bar_cluster", value);
	}
}
