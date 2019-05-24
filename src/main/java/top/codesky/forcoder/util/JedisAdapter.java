package top.codesky.forcoder.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import top.codesky.forcoder.common.properties.RedisProperties;

import java.util.List;
import java.util.Set;

/**
 * @Date: 2019/5/12 13:51
 * @Author: codesky
 * @Description: 基于Jedis封装redis操作
 */
@Repository
@Slf4j
public class JedisAdapter {

    private final JedisPool jedisPool;

    @Autowired
    public JedisAdapter(RedisProperties redisProperties) {
        this.jedisPool = new JedisPool(redisProperties.getHost(), redisProperties.getPort());
    }

    public Jedis getJedis() {
        return jedisPool.getResource();
    }

    public long sadd(String key, String value) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.sadd(key, value);
        }
    }

    public long srem(String key, String value) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.srem(key, value);
        }
    }

    public long scard(String key) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.scard(key);
        }
    }

    public boolean sismember(String key, String value) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.sismember(key, value);
        }
    }

    public long zcard(String key) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.zcard(key);
        }
    }

    public Set<String> zrevrange(String key, long start, long end) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.zrevrange(key, start, end);
        }
    }

    public Double zscore(String key, String member) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.zscore(key, member);
        }
    }

    public long lpush(String key, String value) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.lpush(key, value);
        }
    }

    public List<String> brpop(final int timeout,final String key) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.brpop(timeout,key);
        }
    }



}
