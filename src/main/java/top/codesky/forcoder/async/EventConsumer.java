package top.codesky.forcoder.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
import top.codesky.forcoder.util.JedisAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Date: 2019/5/24 9:20
 * @Author: codesky
 * @Description: 事件消费者，启动事件处理线程
 */
@Service
@Slf4j
public class EventConsumer implements InitializingBean, ApplicationContextAware {
    private final JedisAdapter jedisAdapter;
    private ApplicationContext applicationContext;

    @Autowired
    public EventConsumer(JedisAdapter jedisAdapter) {
        this.jedisAdapter = jedisAdapter;
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        //注册事件处理器
        Map<EventType, List<EventHandler>> handlerRegistryMap = new HashMap<>();
        //获取spring容器中注册的EventHandler类型的bean
        Map<String, EventHandler> beans = applicationContext.getBeansOfType(EventHandler.class);
        if (beans != null) {
            for (Map.Entry<String, EventHandler> entry : beans.entrySet()) {
                List<EventType> eventTypes = entry.getValue().getSupportEventTypes();
                for (EventType eventType : eventTypes) {
                    if (!handlerRegistryMap.containsKey(eventType)) {
                        handlerRegistryMap.put(eventType, new ArrayList<>());
                    }
                    handlerRegistryMap.get(eventType).add(entry.getValue());
                }
            }
        }
        EventConsumerThread eventConsumerThread = new EventConsumerThread(jedisAdapter, handlerRegistryMap);
        eventConsumerThread.start();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
