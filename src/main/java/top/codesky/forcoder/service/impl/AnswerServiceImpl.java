package top.codesky.forcoder.service.impl;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;
import top.codesky.forcoder.dao.AnswerMapper;
import top.codesky.forcoder.model.entity.Answer;
import top.codesky.forcoder.model.vo.AnswerDetailsVo;
import top.codesky.forcoder.service.AnswerService;

import java.util.Date;
import java.util.List;

/**
 * @Date: 2019/4/25 17:58
 * @Author: codesky
 * @Description: 问题相关业务
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
        answer.setAuthorId(authorId);
        answer.setQuestionId(questionId);

        answer.setContent(content);
        answer.setAnonymously(false);

        Date currentDate = new Date();
        answer.setGmtCreated(currentDate);
        answer.setGmtModified(currentDate);

        //提取出部分的回答摘要
        Document doc = Jsoup.parse(content);
        String text = doc.body().text();
        String excerpt;
        if (text.length() <= 200) {
            excerpt = text;
        } else {
            excerpt = text.substring(0, 200);
        }
        answer.setExcerpt(excerpt);

        return answerMapper.insertSelective(answer) > 0;
    }

    @Override
    public List<AnswerDetailsVo> getAnswersByQuestionId(Long questionId) {
        return answerMapper.selectAnswersByQuestionId(questionId);
    }


}
