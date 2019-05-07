package top.codesky.forcoder.service.impl;

import org.springframework.stereotype.Service;
import top.codesky.forcoder.dao.AnswerMapper;
import top.codesky.forcoder.model.entity.Answer;
import top.codesky.forcoder.service.AnswerService;

import java.util.Date;

/**
 * @Date: 2019/4/25 17:58
 * @Author: codesky
 * @Description: forcoder
 */
@Service
public class AnswerServiceImpl implements AnswerService {

    private final AnswerMapper answerMapper;

    public AnswerServiceImpl(AnswerMapper answerMapper) {
        this.answerMapper = answerMapper;
    }

    @Override
    public boolean addAnswer(Long questionId, Long authorId, String content) {
        Answer answer = new Answer();
        answer.setAuthorId(questionId);
        answer.setQuestionId(questionId);
        answer.setContent(content);

        Date currentDate = new Date();
        answer.setGmtCreate(currentDate);
        answer.setGmtModified(currentDate);
//        answer.setExcerpt("");

        return answerMapper.insertSelective(answer) > 0;
    }
}
