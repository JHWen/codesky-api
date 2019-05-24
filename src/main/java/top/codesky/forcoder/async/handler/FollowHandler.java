package top.codesky.forcoder.async.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.codesky.forcoder.async.EventHandler;
import top.codesky.forcoder.async.EventModel;
import top.codesky.forcoder.async.EventType;
import top.codesky.forcoder.common.constant.EntityType;
import top.codesky.forcoder.dao.MessageMapper;
import top.codesky.forcoder.model.entity.Message;
import top.codesky.forcoder.model.entity.Question;
import top.codesky.forcoder.model.entity.UserAdditionInfo;
import top.codesky.forcoder.service.QuestionService;
import top.codesky.forcoder.service.UserService;
import top.codesky.forcoder.util.CodeskyUtils;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @Date: 2019/5/24 10:00
 * @Author: codesky
 * @Description: 处理关注事件
 */
@Component
@Slf4j
public class FollowHandler implements EventHandler {
    private static final String question_content_format = "%s关注了你提出的问题：%s";
    private static final String member_content_format = "%s关注了你";

    private final UserService userService;
    private final QuestionService questionService;
    private final MessageMapper messageMapper;

    @Autowired
    public FollowHandler(UserService userService, QuestionService questionService, MessageMapper messageMapper) {
        this.userService = userService;
        this.questionService = questionService;
        this.messageMapper = messageMapper;
    }

    @Override
    public void doHandler(EventModel eventModel) {
        log.debug("handle follow event:{}", eventModel.toString());
        Message message = new Message();
        message.setFromId(CodeskyUtils.DEFAULT_SYSTEM_ID);
        Date currentDate = new Date();
        message.setGmtCreate(currentDate);
        message.setGmtModified(currentDate);
        message.setToId(eventModel.getEntityOwnerId());

        UserAdditionInfo userAdditionInfo = userService.getPublicationsOfMember(eventModel.getActorId());

        //关注问题
        if (eventModel.getEntityType() == EntityType.QUESTION) {
            Question question = questionService.getQuestionById(eventModel.getEntityId());
            message.setContent(String.format(question_content_format, userAdditionInfo.getUsername(),
                    question.getTitle()));
        } else if (eventModel.getEntityType() == EntityType.MEMBER) {
            message.setContent(String.format(member_content_format, userAdditionInfo.getUsername()));
        }
        message.setRead(false);
        message.setConversionId(message.getFromId() + "_" + message.getToId());
        messageMapper.insertMessage(message);
        log.debug("create message:{}", message.toString());

    }

    @Override
    public List<EventType> getSupportEventTypes() {
        return Collections.singletonList(EventType.FOLLOW);
    }
}
