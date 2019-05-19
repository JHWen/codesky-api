package top.codesky.forcoder.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import top.codesky.forcoder.common.constant.ItemType;
import top.codesky.forcoder.common.exception.ServiceException;
import top.codesky.forcoder.dao.AnswerMapper;
import top.codesky.forcoder.dao.QuestionMapper;
import top.codesky.forcoder.model.entity.Question;
import top.codesky.forcoder.model.params.QuestionDeleteParams;
import top.codesky.forcoder.model.vo.AnswerDetailsVO;
import top.codesky.forcoder.model.vo.QuestionDetailsVO;
import top.codesky.forcoder.model.vo.QuestionItemVO;
import top.codesky.forcoder.model.vo.QuestionWithAuthor;
import top.codesky.forcoder.service.QuestionService;
import top.codesky.forcoder.util.BeanUtils;

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
     * @param title   not empty
     * @param content null auto to "",content
     * @param userId  user_id
     * @return
     */
    @Override
    @NonNull
    public Question addQuestion(String title, String content, Long userId) {
        //构造回答
        Question question = new Question();
        question.setTitle(title);
        question.setContent(content == null ? "" : content);
        question.setAuthorId(userId);
        Date currentDate = new Date();
        question.setGmtCreate(currentDate);
        question.setGmtModified(currentDate);

        int res = questionMapper.insert(question);
        if (res == 0) {
            throw new ServiceException("failed to add question");
        }

        return question;
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
     * @return List<QuestionItemVO>
     */
    @Override
    @NonNull
    public List<QuestionItemVO> getLatestQuestions(long offset, long limit) {
        List<QuestionWithAuthor> questionWithAuthors = questionMapper.selectLatestQuestionByPage(offset, limit);

        List<QuestionItemVO> questionItems = new ArrayList<>();

        for (QuestionWithAuthor questionWithAuthor : questionWithAuthors) {
            QuestionItemVO questionItem = BeanUtils.copyPropertiesFrom(questionWithAuthor, QuestionItemVO.class);
            //find answer ：choose one answer for question
            AnswerDetailsVO answerDetailsVo = answerMapper.selectAnswerByQuestionId(questionItem.getId());
            if (answerDetailsVo != null) {
                //question+answer type
                questionItem.setType(ItemType.answer);
                questionItem.setAnswer(answerDetailsVo);
            } else {
                //only question type
                questionItem.setType(ItemType.question);
            }
            questionItems.add(questionItem);
        }
        return questionItems;
    }

    /**
     * 通过question id，获取问题的详细信息, 如问题描述和提问者信息
     *
     * @param questionId question id
     * @return QuestionDetailsVO (question + author)
     */
    @Override
    public QuestionDetailsVO getQuestionDetailsByQuestionId(Long questionId) {
        return questionMapper.selectQuestionDetailsByQuestionId(questionId);
    }

}
