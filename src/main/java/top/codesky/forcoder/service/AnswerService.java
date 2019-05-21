package top.codesky.forcoder.service;

import top.codesky.forcoder.common.constant.ProcessStatusEnum;
import top.codesky.forcoder.model.vo.AnswerDetailsVO;

import java.util.List;

/**
 * @Date: 2019/4/25 17:56
 * @Author: codesky
 * @Description: 回答相关Service
 */
public interface AnswerService {

    ProcessStatusEnum addAnswer(Long questionId, Long authorId, String content);

    List<AnswerDetailsVO> getAnswersByQuestionId(Long questionId);

    AnswerDetailsVO getAnswerDetailsByAnswerId(long answerId);

    boolean answerHasExisted(long answerId);
}
