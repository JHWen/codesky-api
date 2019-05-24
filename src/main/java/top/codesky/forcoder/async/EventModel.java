package top.codesky.forcoder.async;

import lombok.*;
import lombok.experimental.Accessors;
import top.codesky.forcoder.common.constant.EntityType;

/**
 * @Date: 2019/5/22 21:30
 * @Author: codesky
 * @Description: 异步事件模型
 */
@Accessors(chain = true)
@Data(staticConstructor = "of")
@NoArgsConstructor
public class EventModel {
    @NonNull
    private EventType eventType;
    //事件发起者
    private long actorId;
    private EntityType entityType;
    private long entityId;
    //对于点赞和关注问题事件，实体所有者有问题的作者、回答的作者
    private long entityOwnerId;
}
