package top.codesky.forcoder.model.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * @Date: 2019/4/24 20:07
 * @Author: codesky
 * @Description: 返回给前端的问题的详细信息，question+author+answer
 */
@Setter
@Getter
public class QuestionDetailsVo {

    private Long id;
    private String title;
    private String content;
    private int answerCount;
    private int commentCount;
    private int followerCount;
    private Date gmtCreate;
    private Date gmtModified;
    private boolean hasFollow;

    //提问者信息
    private PublicationsOfMemberVO author;

    //回答信息
    private List<AnswerDetailsVo> answers;

}
