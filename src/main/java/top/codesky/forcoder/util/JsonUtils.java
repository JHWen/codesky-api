package top.codesky.forcoder.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.lang.NonNull;
import org.springframework.util.Assert;

import java.io.IOException;

/**
 * @Date: 2019/4/18 16:12
 * @Author: codesky
 * @Description: 单例模式 -> 返回自定义的ObjectMapper
 */
public class JsonUtils {

    private static class ObjectMapperHolder {
        private static ObjectMapper DEFAULT_OBJECT_MAPPER;

        static {
            DEFAULT_OBJECT_MAPPER = new ObjectMapper();
            DEFAULT_OBJECT_MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        }
    }

    private JsonUtils() {

    }

    public static ObjectMapper getObjectMapper() {
        return ObjectMapperHolder.DEFAULT_OBJECT_MAPPER;
    }

    public static String objectToJson(@NonNull Object source) throws JsonProcessingException {
        Assert.notNull(source, "source object must not null");

        return getObjectMapper().writeValueAsString(source);
    }

    public static <T> T jsonToObject(String json, Class<T> clazz) throws IOException {
        Assert.hasText(json, "json must not blank");
        Assert.notNull(clazz, "clazz must not null");

        return getObjectMapper().readValue(json,clazz);
    }
}
