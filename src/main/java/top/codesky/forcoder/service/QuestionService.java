package top.codesky.forcoder.service;

import top.codesky.forcoder.model.entity.Question;

public interface QuestionService {
    Question addQuestion(String title, String content, Long userId);

    Question getQuestionById(Long id);
}
