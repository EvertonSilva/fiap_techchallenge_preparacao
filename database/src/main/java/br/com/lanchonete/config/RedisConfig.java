package br.com.lanchonete.config;

import java.time.Duration;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisConfig {

    private static volatile JedisPool pool = null;

    public static JedisPool getJedisPool() {
        JedisPool pool = RedisConfig.pool;
        
        if (pool == null) {
            synchronized (RedisConfig.class) {
                pool = RedisConfig.pool;
                if (pool == null) {
                    JedisPoolConfig poolConfig = new JedisPoolConfig();
                    poolConfig.setMaxTotal(128);
                    poolConfig.setMaxIdle(128);
                    poolConfig.setMinIdle(16);
                    poolConfig.setTestOnBorrow(true);
                    poolConfig.setTestOnReturn(true);
                    poolConfig.setTestWhileIdle(true);
                    poolConfig.setMinEvictableIdleTime(Duration.ofSeconds(60));
                    poolConfig.setTimeBetweenEvictionRuns(Duration.ofSeconds(30));
                    poolConfig.setNumTestsPerEvictionRun(3);
                    poolConfig.setBlockWhenExhausted(true);
                    
                    RedisConfig.pool = pool = new JedisPool(poolConfig, "localhost", 6379, 2000);
                }
            }
        }
        return pool;
    }

    public static Jedis getJedis() {
        return getJedisPool().getResource();
    }
}

