package top.codesky.forcoder.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.codesky.forcoder.common.constant.EntityType;
import top.codesky.forcoder.common.constant.VoteStatus;
import top.codesky.forcoder.service.VoteService;
import top.codesky.forcoder.util.JedisAdapter;
import top.codesky.forcoder.util.RedisKeyUtil;

/**
 * @Date: 2019/5/12 13:49
 * @Author: codesky
 * @Description: 实现点赞、点踩业务逻辑
 */
@Service
public class VoteServiceImpl implements VoteService {

    private final JedisAdapter jedisAdapter;

    @Autowired
    public VoteServiceImpl(JedisAdapter jedisAdapter) {
        this.jedisAdapter = jedisAdapter;
    }

    @Override
    public long voteUp(long userId, EntityType entityType, long entityId) {
        //获取实体的点赞key
        String likeKey = RedisKeyUtil.getLikeKey(entityType, entityId);
        jedisAdapter.sadd(likeKey, String.valueOf(userId));

        //获取实体的点踩key
        String disLikeKey = RedisKeyUtil.getDisLikeKey(entityType, entityId);
        jedisAdapter.srem(disLikeKey, String.valueOf(userId));

        return jedisAdapter.scard(likeKey);
    }

    @Override
    public long voteNeutral(long userId, EntityType entityType, long entityId) {
        String likeKey = RedisKeyUtil.getLikeKey(entityType, entityId);
        jedisAdapter.srem(likeKey, String.valueOf(userId));

        String disLikeKey = RedisKeyUtil.getDisLikeKey(entityType, entityId);
        jedisAdapter.srem(disLikeKey, String.valueOf(userId));

        return jedisAdapter.scard(likeKey);
    }


    @Override
    public long voteDown(long userId, EntityType entityType, long entityId) {
        //获取实体的点赞key
        String likeKey = RedisKeyUtil.getLikeKey(entityType, entityId);
        jedisAdapter.srem(likeKey, String.valueOf(userId));

        //获取实体的点踩key
        String disLikeKey = RedisKeyUtil.getDisLikeKey(entityType, entityId);
        jedisAdapter.sadd(disLikeKey, String.valueOf(userId));

        return jedisAdapter.scard(likeKey);
    }

    @Override
    public long getVoteUpCount(EntityType entityType, long entityId) {
        String likeKey = RedisKeyUtil.getLikeKey(entityType, entityId);
        return jedisAdapter.scard(likeKey);
    }

    /**
     * 判断用户对实体的点赞或点踩状态
     *
     * @param userId
     * @param entityType
     * @param entityId
     * @return VoteStatus 点赞（VoteUp） 中立（neutrality） 点踩（VoteDown）
     */
    @Override
    public VoteStatus getVoteStatus(long userId, EntityType entityType, long entityId) {
        String likeKey = RedisKeyUtil.getLikeKey(entityType, entityId);
        if (jedisAdapter.sismember(likeKey, String.valueOf(userId))) {
            return VoteStatus.VOTEUP;
        }

        String disLikeKey = RedisKeyUtil.getDisLikeKey(entityType, entityId);
        if (jedisAdapter.sismember(disLikeKey, String.valueOf(userId))) {
            return VoteStatus.VOTEDOWN;
        }
        return VoteStatus.NEUTRALITY;
    }


}
