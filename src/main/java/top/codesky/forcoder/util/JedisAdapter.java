package top.codesky.forcoder.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import top.codesky.forcoder.common.constant.EntityType;
import top.codesky.forcoder.common.properties.RedisProperties;

import java.util.Set;

/**
 * @Date: 2019/5/12 13:51
 * @Author: codesky
 * @Description: 基于Jedis封装redis操作
 */
@Repository
public class JedisAdapter {
    private static final Logger logger = LoggerFactory.getLogger(JedisAdapter.class);

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


    public static void main(String[] args) {
        JedisPool pool = new JedisPool("localhost", 6379);
        try (Jedis jedis = pool.getResource()) {
            String key = RedisKeyUtil.getLikeKey(EntityType.ANSWER, 1);
            long res = jedis.sadd(key, String.valueOf(1), String.valueOf(5));
            System.out.println(res);
            jedis.sadd(key, String.valueOf(2));
            jedis.sadd(key, String.valueOf(3));
            jedis.sadd(key, String.valueOf(4));
            System.out.println(jedis.smembers(key));
            System.out.println(jedis.smembers("heheda"));
            System.out.println(jedis.scard(key));
            jedis.zadd("test", 2L, "12");
        }
        pool.close();

    }

}
