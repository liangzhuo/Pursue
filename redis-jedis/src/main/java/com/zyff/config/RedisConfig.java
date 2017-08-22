package com.zyff.config;

/**
 * redis配置
 * @author liangz
 *
 */
public interface RedisConfig {
	//可用连接实例的最大数目，默认值为8；
    //如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
	int MAX_TOTAL = 1024;
	
	//控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
	int MAX_IDLE = 200;
	
	//等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；
	int MAX_WAIT = 10000;
	
	int TIMEOUT = 10000;
	
	int RETRY_NUM = 5;
}
