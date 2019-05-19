package top.codesky.forcoder.model.vo;

import lombok.Data;

import java.util.Date;

/**
 * @Date: 2019/5/19 15:02
 * @Author: codesky
 * @Description: 添加问题成功后的返回问题内容
 */
@Data
public class QuestionVO {
    private Long id;
    private String title;
    private String content;
    private Date gmtCreate;
    private Date gmtModified;
}
