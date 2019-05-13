package top.codesky.forcoder.service;

import top.codesky.forcoder.common.constant.EntityType;

import java.util.List;

/**
 * @Date: 2019/5/12 15:39
 * @Author: codesky
 * @Description: 关注、被关注业务逻辑
 */
public interface FollowService {
    boolean follow(long userId, EntityType entityType, long entityId);

    boolean unfollow(long userId, EntityType entityType, long entityId);

    long getFollowerCount(EntityType entityType, long entityId);

    long getFolloweeCount(long userId, EntityType entityType);

    List<Long> getFollowees(long userId, EntityType entityType, long limit);

    List<Long> getFollowees(long userId, EntityType entityType, long offset, long limit);

    List<Long> getFollowers(EntityType entityType, long entityId, long limit);

    List<Long> getFollowers(EntityType entityType, long entityId, long offset, long limit);

    boolean isFollower(long userId, EntityType entityType, long entityId);
}
