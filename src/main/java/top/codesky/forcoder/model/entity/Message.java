package top.codesky.forcoder.model.entity;

import lombok.Data;

import java.util.Date;

/**
 * @Date: 2019/5/24 10:04
 * @Author: codesky
 * @Description: 事件处理转化成对应的消息发送给用户
 */
@Data
public class Message {
    private long id;
    private long fromId;
    private long toId;
    private String content;
    private Date gmtCreate;
    private Date gmtModified;
    private boolean read;
    private String conversionId;
}
