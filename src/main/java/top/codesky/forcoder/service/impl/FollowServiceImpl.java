package top.codesky.forcoder.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Response;
import redis.clients.jedis.Transaction;
import top.codesky.forcoder.common.constant.EntityType;
import top.codesky.forcoder.service.FollowService;
import top.codesky.forcoder.util.JedisAdapter;
import top.codesky.forcoder.util.RedisKeyUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @Date: 2019/5/12 15:49
 * @Author: codesky
 * @Description: 关注与被关注业务逻辑：目前可以关注的实体有问题和用户
 */
@Service
@Slf4j
public class FollowServiceImpl implements FollowService {


    private final JedisAdapter jedisAdapter;

    @Autowired
    public FollowServiceImpl(JedisAdapter jedisAdapter) {
        this.jedisAdapter = jedisAdapter;
    }

    /**
     * 关注某个实体：问题 or 用户
     *
     * @param userId
     * @param entityType
     * @param entityId
     * @return
     */
    @Override
    public boolean follow(long userId, EntityType entityType, long entityId) {
        try (Jedis jedis = jedisAdapter.getJedis();
             Transaction tx = jedis.multi()) {
            String followerKey = RedisKeyUtils.getFollowerKey(entityType, entityId);
            String followeeKey = RedisKeyUtils.getFolloweeKey(userId, entityType);

            //根据时间戳对关注实体id排序
            Date date = new Date();

            //实体的被关注列表增加粉丝
            Response<Long> addFollowerRes = tx.zadd(followerKey, date.getTime(), String.valueOf(userId));
            //当前用户的关注者列表增加
            Response<Long> addFolloweeRes = tx.zadd(followeeKey, date.getTime(), String.valueOf(entityId));
            tx.exec();
            return addFollowerRes.get() > 0 && addFolloweeRes.get() > 0;
        }
    }

    /**
     * 取消关注实体
     *
     * @param userId
     * @param entityType
     * @param entityId
     * @return
     */
    @Override
    public boolean unfollow(long userId, EntityType entityType, long entityId) {
        try (Jedis jedis = jedisAdapter.getJedis();
             Transaction tx = jedis.multi()) {
            String followerKey = RedisKeyUtils.getFollowerKey(entityType, entityId);
            String followeeKey = RedisKeyUtils.getFolloweeKey(userId, entityType);

            //实体的被关注列表增加粉丝
            Response<Long> addFollowerRes = tx.zrem(followerKey, String.valueOf(userId));
            //当前用户的关注者列表增加
            Response<Long> addFolloweeRes = tx.zrem(followeeKey, String.valueOf(entityId));

            tx.exec();
            return addFollowerRes.get() > 0 && addFolloweeRes.get() > 0;
        }
    }

    @Override
    public long getFollowerCount(EntityType entityType, long entityId) {
        String followerKey = RedisKeyUtils.getFollowerKey(entityType, entityId);
        return jedisAdapter.zcard(followerKey);
    }

    @Override
    public long getFolloweeCount(long userId, EntityType entityType) {
        String followeeKey = RedisKeyUtils.getFolloweeKey(userId, entityType);
        return jedisAdapter.zcard(followeeKey);
    }

    @Override
    public List<Long> getFollowees(long userId, EntityType entityType, long limit) {
        String followeeKey = RedisKeyUtils.getFolloweeKey(userId, entityType);
        return getIdsFromSet(jedisAdapter.zrevrange(followeeKey, 0, limit));
    }

    @Override
    public List<Long> getFollowees(long userId, EntityType entityType, long offset, long limit) {
        String followeeKey = RedisKeyUtils.getFolloweeKey(userId, entityType);
        return getIdsFromSet(jedisAdapter.zrevrange(followeeKey, offset, limit));
    }

    @Override
    public List<Long> getFollowers(EntityType entityType, long entityId, long limit) {
        String followerKey = RedisKeyUtils.getFollowerKey(entityType, entityId);
        return getIdsFromSet(jedisAdapter.zrevrange(followerKey, 0, limit));
    }

    @Override
    public List<Long> getFollowers(EntityType entityType, long entityId, long offset, long limit) {
        String followerKey = RedisKeyUtils.getFollowerKey(entityType, entityId);
        return getIdsFromSet(jedisAdapter.zrevrange(followerKey, offset, limit));
    }


    private List<Long> getIdsFromSet(Set<String> idSet) {
        List<Long> ids = new ArrayList<>();
        for (String s : idSet) {
            ids.add(Long.parseLong(s));
        }
        return ids;
    }

    @Override
    public boolean isFollower(long userId, EntityType entityType, long entityId) {
        String followerKey = RedisKeyUtils.getFollowerKey(entityType, entityId);
        return jedisAdapter.zscore(followerKey, String.valueOf(userId)) != null;
    }
}
