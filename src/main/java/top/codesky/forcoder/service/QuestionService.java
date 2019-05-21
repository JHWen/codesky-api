package top.codesky.forcoder.service;

import top.codesky.forcoder.common.constant.ProcessStatusEnum;
import top.codesky.forcoder.model.entity.Question;
import top.codesky.forcoder.model.vo.QuestionDetailsVO;
import top.codesky.forcoder.model.vo.QuestionItemVO;

import java.util.List;

public interface QuestionService {
    boolean questionHasExisted(long questionId);

    Question addQuestion(String title, String content, Long userId);

    Question getQuestionById(Long id);

    ProcessStatusEnum deleteQuestion(Long questionId, Long userId);

    List<QuestionItemVO> getLatestQuestions(long offset, long limit);

    QuestionDetailsVO getQuestionDetailsByQuestionId(Long questionId);
}
