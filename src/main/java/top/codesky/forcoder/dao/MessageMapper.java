package top.codesky.forcoder.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import top.codesky.forcoder.model.entity.Message;

/**
 * @Date: 2019/5/24 10:30
 * @Author: codesky
 * @Description: 消息数据访问层
 */
@Mapper
@Repository
public interface MessageMapper {

    /**
     * 插入消息
     *
     * @param message 消息对象
     * @return 返回影响的行数
     */
    int insertMessage(Message message);


}
