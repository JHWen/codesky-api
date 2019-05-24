package top.codesky.forcoder.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.codesky.forcoder.util.JedisAdapter;
import top.codesky.forcoder.util.JsonUtils;
import top.codesky.forcoder.util.RedisKeyUtils;

/**
 * @Date: 2019/5/22 21:51
 * @Author: codesky
 * @Description: 事件发起者
 */
@Service
@Slf4j
public class EventProducer {
    //redis作为事件队列
    private final JedisAdapter jedisAdapter;

    @Autowired
    public EventProducer(JedisAdapter jedisAdapter) {
        this.jedisAdapter = jedisAdapter;
    }

    public boolean fireEvent(EventModel eventModel) {
        try {
            String json = JsonUtils.objectToJson(eventModel);
            String key = RedisKeyUtils.getEventQueueKey();
            return jedisAdapter.lpush(key, json) > 0;
        } catch (Exception exception) {
            log.error("eventModel push failed:{}", exception.getMessage());
        }
        return false;
    }


}
