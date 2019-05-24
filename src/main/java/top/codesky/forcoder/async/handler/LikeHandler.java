package top.codesky.forcoder.async.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.codesky.forcoder.async.EventHandler;
import top.codesky.forcoder.async.EventModel;
import top.codesky.forcoder.async.EventType;
import top.codesky.forcoder.dao.MessageMapper;
import top.codesky.forcoder.model.entity.Message;
import top.codesky.forcoder.model.entity.UserAdditionInfo;
import top.codesky.forcoder.service.UserService;
import top.codesky.forcoder.util.CodeskyUtils;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @Date: 2019/5/24 10:01
 * @Author: codesky
 * @Description: 对回答的点赞事件处理器
 */
@Component
@Slf4j
public class LikeHandler implements EventHandler {
    private static final String like_content_format = "%s点赞了你的回答";

    private final UserService userService;
    private final MessageMapper messageMapper;

    @Autowired
    public LikeHandler(UserService userService, MessageMapper messageMapper) {
        this.userService = userService;
        this.messageMapper = messageMapper;
    }

    @Override
    public void doHandler(EventModel eventModel) {
        log.debug("handler like event:{}", eventModel.toString());
        //构造消息，这些事件当成系统发送的消息
        Message message = new Message();
        message.setFromId(CodeskyUtils.DEFAULT_SYSTEM_ID);
        message.setToId(eventModel.getEntityOwnerId());
        Date currentDate = new Date();
        message.setGmtCreate(currentDate);
        message.setGmtModified(currentDate);

        UserAdditionInfo userAdditionInfo = userService.getPublicationsOfMember(eventModel.getActorId());

        message.setContent(String.format(like_content_format, userAdditionInfo.getUsername()));

        message.setRead(false);
        message.setConversionId(message.getFromId() + "_" + message.getToId());
        messageMapper.insertMessage(message);
        log.debug("create message:{}", message.toString());
    }

    @Override
    public List<EventType> getSupportEventTypes() {
        return Collections.singletonList(EventType.LIKE);
    }
}
