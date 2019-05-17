package top.codesky.forcoder.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.codesky.forcoder.common.constant.EntityType;
import top.codesky.forcoder.common.constant.ItemType;
import top.codesky.forcoder.dao.AnswerMapper;
import top.codesky.forcoder.dao.QuestionMapper;
import top.codesky.forcoder.model.entity.Question;
import top.codesky.forcoder.model.params.QuestionDeleteParams;
import top.codesky.forcoder.model.vo.AnswerDetailsVo;
import top.codesky.forcoder.model.vo.QuestionDetailsVo;
import top.codesky.forcoder.model.vo.QuestionItemVo;
import top.codesky.forcoder.model.vo.QuestionWithAuthor;
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

    /**
     * 分页查询最新的问答数据
     *
     * @param offset as pageNum
     * @param limit  as pageSize
     * @return List<QuestionItemVo>
     */
    @Override
    public List<QuestionItemVo> getLatestQuestions(long offset, long limit) {
        List<QuestionWithAuthor> questionWithAuthors = questionMapper.selectLatestQuestionByPage(offset, limit);
        //choose one answer for question
        List<QuestionItemVo> questionItems = new ArrayList<>();
        for(QuestionWithAuthor questionWithAuthor : questionWithAuthors) {
            QuestionItemVo questionItem = new QuestionItemVo();
            questionItem.setQuestion(questionWithAuthor);
            //find answer
            AnswerDetailsVo answerDetailsVo = answerMapper.selectAnswerByQuestionId(questionItem.getId());
            if (answerDetailsVo != null) {
                //question+answer
                questionItem.setType(ItemType.answer);
                questionItem.setAnswer(answerDetailsVo);
            } else {
                //only question
                questionItem.setType(ItemType.question);
            }
            questionItems.add(questionItem);
        }
        return questionItems;
    }

    @Override
    public QuestionDetailsVo getQuestionDetailsByQuestionId(Long questionId) {
        return questionMapper.selectQuestionDetailsByQuestionId(questionId);
    }

}
