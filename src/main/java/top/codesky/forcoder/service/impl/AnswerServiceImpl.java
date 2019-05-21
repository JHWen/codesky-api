package top.codesky.forcoder.service.impl;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;
import top.codesky.forcoder.common.constant.ProcessStatusEnum;
import top.codesky.forcoder.dao.AnswerMapper;
import top.codesky.forcoder.model.entity.Answer;
import top.codesky.forcoder.model.params.AnswerCountQuery;
import top.codesky.forcoder.model.vo.AnswerDetailsVO;
import top.codesky.forcoder.service.AnswerService;
import top.codesky.forcoder.util.CodeskyUtils;

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
    public ProcessStatusEnum addAnswer(Long questionId, Long authorId, String content) {
        //1.判断用户是否已经作答
        AnswerCountQuery answerCountQuery = new AnswerCountQuery(questionId, authorId);
        int answerCount = answerMapper.countUserAnswerByQuestionId(answerCountQuery);
        if (answerCount > 0) {
            return ProcessStatusEnum.RECORD_HAS_EXISTED;
        }

        // 2.添加回答
        Answer answer = new Answer();
        answer.setAuthorId(authorId);
        answer.setQuestionId(questionId);

        // 防止xss,过滤html标签
        answer.setContent(CodeskyUtils.cleanHtml(content));
        answer.setAnonymously(false);

        Date currentDate = new Date();
        answer.setGmtCreate(currentDate);
        answer.setGmtModified(currentDate);

        // 提取出回答摘要，默认开头的200字
        Document doc = Jsoup.parse(content);
        String text = doc.body().text().trim();
        String excerpt;
        if (text.length() <= 200) {
            excerpt = text;
        } else {
            excerpt = text.substring(0, 200);
        }
        answer.setExcerpt(excerpt);

        // 3.插入数据库
        int res = answerMapper.insertSelective(answer);
        return res > 0 ? ProcessStatusEnum.SUCCESS : ProcessStatusEnum.FAIL;

    }

    @Override
    public List<AnswerDetailsVO> getAnswersByQuestionId(Long questionId) {
        return answerMapper.selectAnswersByQuestionId(questionId);
    }

    @Override
    public AnswerDetailsVO getAnswerDetailsByAnswerId(long answerId) {
        return answerMapper.selectAnswerByAnswerId(answerId);
    }

    @Override
    public boolean answerHasExisted(long answerId) {
        return answerMapper.countAnswerByAnswerId(answerId) == 1;
    }

}
