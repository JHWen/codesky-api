package top.codesky.forcoder.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.codesky.forcoder.common.constant.IndexItemType;
import top.codesky.forcoder.dao.AnswerMapper;
import top.codesky.forcoder.dao.QuestionMapper;
import top.codesky.forcoder.model.entity.Question;
import top.codesky.forcoder.model.entity.QuestionWithAuthor;
import top.codesky.forcoder.model.params.QuestionDeleteParams;
import top.codesky.forcoder.model.vo.AnswerDetailsVo;
import top.codesky.forcoder.model.vo.QuestionDetailsVo;
import top.codesky.forcoder.model.vo.QuestionItemVo;
import top.codesky.forcoder.service.QuestionService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionMapper questionMapper;
    private final AnswerMapper answerMapper;

    @Autowired
    public QuestionServiceImpl(QuestionMapper questionMapper, AnswerMapper answerMapper) {
        this.questionMapper = questionMapper;
        this.answerMapper = answerMapper;
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

    @Override
    public boolean deleteQuestion(Long questionId, Long authorId) {
        QuestionDeleteParams deleteParams = new QuestionDeleteParams(questionId, authorId);
        return questionMapper.deleteByQuestionIdAndUserId(deleteParams) > 0;
    }

    @Override
    public List<QuestionItemVo> getLatestQuestions(long offset, long limit) {
        List<QuestionWithAuthor> questionWithAuthors = questionMapper.selectLatestQuestionByPage(offset, limit);
        //find one answer for index show
        List<QuestionItemVo> questions = new ArrayList<>();
        for(QuestionWithAuthor question : questionWithAuthors) {
            QuestionItemVo item = new QuestionItemVo();
            item.setQuestion(question);
            //find answer
            AnswerDetailsVo answerDetailsVo = answerMapper.selectAnswerByQuestionId(question.getId());
            if (answerDetailsVo != null) {
                item.setType(IndexItemType.answer);
                item.setAnswer(answerDetailsVo);
            } else {
                item.setType(IndexItemType.question);
            }
            questions.add(item);
        }
        return questions;
    }

    @Override
    public QuestionDetailsVo getQuestionDetailsByQuestionId(Long questionId) {
        return questionMapper.selectQuestionDetailsByQuestionId(questionId);
    }

}
