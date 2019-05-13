package top.codesky.forcoder.model.vo;

/**
 * @Date: 2019/5/12 14:56
 * @Author: codesky
 * @Description: 封装赞踩提交数据：
 * <p>
 * 点赞:type:"up"
 * 点踩：type:"down"
 * 中立：type:"neutral"
 * </p>
 */
public class VotePostVo {
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

