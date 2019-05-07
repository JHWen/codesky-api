package top.codesky.forcoder.service;

import top.codesky.forcoder.model.vo.AnswerDetailsVo;

import java.util.List;

/**
 * @Date: 2019/4/25 17:56
 * @Author: codesky
 * @Description: 回答相关Service
 */
public interface AnswerService {

    boolean addAnswer(Long questionId, Long authorId, String content);

    List<AnswerDetailsVo> getAnswersByQuestionId(Long questionId);
}
