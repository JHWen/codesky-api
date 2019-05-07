package top.codesky.forcoder.service;

import top.codesky.forcoder.model.entity.Question;
import top.codesky.forcoder.model.entity.QuestionWithAuthor;
import top.codesky.forcoder.model.vo.QuestionDetailsVo;

import java.util.List;

public interface QuestionService {
    Question addQuestion(String title, String content, Long userId);

    Question getQuestionById(Long id);

    boolean deleteQuestion(Long questionId, Long userId);

    List<QuestionWithAuthor> getLatestQuestions(long offset, long limit);

    QuestionDetailsVo getQuestionDetailsByQuestionId(Long questionId);
}
