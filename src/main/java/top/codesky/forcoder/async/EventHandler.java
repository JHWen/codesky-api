package top.codesky.forcoder.async;

import java.util.List;

/**
 * @Date: 2019/5/22 21:47
 * @Author: codesky
 * @Description: 事件处理器接口
 */
public interface EventHandler {
    void doHandler(EventModel eventModel);

    List<EventType> getSupportEventTypes();
}
