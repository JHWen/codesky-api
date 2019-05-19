package top.codesky.forcoder.model.vo;

import lombok.Getter;
import lombok.Setter;
import top.codesky.forcoder.common.constant.ItemType;

import java.util.Date;

/**
 * @Date: 2019/5/15 15:27
 * @Author: codesky
 * @Description: 问题+一个回答，用于首页显示
 */
@Getter
@Setter
public class QuestionItemVO {

    private ItemType type;
    private Long id;
    private String title;
    private String content;
    private int answerCount;
    private int commentCount;
    private int followerCount;
    private Date gmtCreate;
    private Date gmtModified;
    private boolean hasFollow;

    private PublicationsOfMemberVO author;

    private AnswerDetailsVO answer;

}
