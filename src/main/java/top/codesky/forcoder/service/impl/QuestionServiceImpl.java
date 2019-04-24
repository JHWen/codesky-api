package top.codesky.forcoder.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.codesky.forcoder.dao.QuestionMapper;
import top.codesky.forcoder.model.entity.Question;
import top.codesky.forcoder.service.QuestionService;

import java.util.Date;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionMapper questionMapper;

    @Autowired
    public QuestionServiceImpl(QuestionMapper questionMapper) {
        this.questionMapper = questionMapper;
    }

    /**
     * 添加回答
     *
     * @param title
     * @param content
     * @param userId
     * @return
     */
    @Override
    public Question addQuestion(String title, String content, Long userId) {
        //构造回答
        Question question = new Question();
        question.setTitle(title);
        question.setContent(content);
        question.setAuthorId(userId);
        Date currentDate = new Date();
        question.setGmtCreate(currentDate);
        question.setGmtModified(currentDate);

        int res = questionMapper.insert(question);

        return res > 0 ? question : null;
    }

    @Override
    public Question getQuestionById(Long id) {
        return questionMapper.selectByPrimaryKey(id);
    }


}
