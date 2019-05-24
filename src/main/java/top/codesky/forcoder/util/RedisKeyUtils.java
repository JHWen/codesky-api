package top.codesky.forcoder.util;

import top.codesky.forcoder.common.constant.EntityType;

/**
 * @Date: 2019/4/20 12:05
 * @Author: codesky
 * @Description: RedisKey工具类 : 生成点赞、关注操作相关的key，如 LIKE:1:ANSWER
 */
public class RedisKeyUtils {

    private static final String SPLIT = ":";
    //赞、踩功能
    private static final String BIZ_LIKE = "LIKE";
    private static final String BIZ_DISLIKE = "DIS_LIKE";

    // 关注、被关注：粉丝
    private static final String BIZ_FOLLOWER = "FOLLOWER";
    private static final String BIZ_FOLLOWEE = "FOLLOWEE";

    //事件队列
    private static final String BIZ_EVENT_QUEUE = "EVENT_QUEUE";

    private RedisKeyUtils() {

    }

    //点赞、踩的key

    public static String getLikeKey(EntityType entityType, long entityId) {
        return BIZ_LIKE + SPLIT + entityType + SPLIT + entityId;
    }

    public static String getDisLikeKey(EntityType entityType, long entityId) {
        return BIZ_DISLIKE + SPLIT + entityType + SPLIT + entityId;
    }

    //用户关注的实体（followee）：问题 用户等
    public static String getFolloweeKey(long userId, EntityType entityType) {
        return BIZ_FOLLOWEE + SPLIT + userId + SPLIT + entityType;
    }

    //实体的被关注者，如粉丝（follower）
    public static String getFollowerKey(EntityType entityType, long entityId) {
        return BIZ_FOLLOWER + SPLIT + entityType + SPLIT + entityId;
    }

    public static String getEventQueueKey() {
        return BIZ_EVENT_QUEUE;
    }
}
