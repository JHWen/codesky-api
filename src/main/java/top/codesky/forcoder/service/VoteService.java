package top.codesky.forcoder.service;

import top.codesky.forcoder.common.constant.EntityType;
import top.codesky.forcoder.common.constant.VoteStatus;

/**
 * @Date: 2019/5/12 13:46
 * @Author: codesky
 * @Description: 点赞或点踩业务逻辑层
 */
public interface VoteService {
    long voteUp(long userId, EntityType entityType, long entityId);

    long voteNeutral(long userId, EntityType entityType, long entityId);

    long voteDown(long userId, EntityType entityType, long entityId);

    long getVoteUpCount(EntityType entityType, long entityId);

    VoteStatus getVoteStatus(long userId, EntityType entityType, long entityId);
}
