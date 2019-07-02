package top.codesky.forcoder.async;

import lombok.extern.slf4j.Slf4j;
import top.codesky.forcoder.util.JedisAdapter;
import top.codesky.forcoder.util.JsonUtils;
import top.codesky.forcoder.util.RedisKeyUtils;

import java.util.List;
import java.util.Map;

/**
 * @Date: 2019/5/24 9:20
 * @Author: codesky
 * @Description: 事件消费者处理线程
 */
@Slf4j
public class EventConsumerThread extends Thread {
    private JedisAdapter jedisAdapter;
    private Map<EventType, List<EventHandler>> handlerRegistryMap;

    public EventConsumerThread(JedisAdapter jedisAdapter, Map<EventType, List<EventHandler>> handlerRegistryMap) {
        super();
        this.jedisAdapter = jedisAdapter;
        this.handlerRegistryMap = handlerRegistryMap;
    }

    @Override
    public void run() {
        String key = RedisKeyUtils.getEventQueueKey();
        while (true) {
            try {

                List<String> events = jedisAdapter.brpop(0, key);
                for (String event : events) {
                    if (event.equals(key)) {
                        continue;
                    }

                    EventModel eventModel = JsonUtils.jsonToObject(event, EventModel.class);
                    if (!handlerRegistryMap.containsKey(eventModel.getEventType())) {
                        log.error("不能识别的事件类型");
                        continue;
                    }

                    for (EventHandler handler : handlerRegistryMap.get(eventModel.getEventType())) {
                        handler.doHandler(eventModel);
                    }
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }
}
