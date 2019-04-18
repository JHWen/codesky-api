package top.codesky.forcoder.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @Date: 2019/4/18 16:12
 * @Author: codesky
 * @Description: 单例模式 -> 返回自定义的ObjectMapper
 */
public class JsonUtil {

    private static class ObjectMapperHolder {
        private static ObjectMapper objectMapper;

        static {
            objectMapper = new ObjectMapper();
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        }
    }

    public static ObjectMapper getObjectMapper() {
        return ObjectMapperHolder.objectMapper;
    }
}
