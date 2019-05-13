package top.codesky.forcoder.common.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Date: 2019/5/12 14:15
 * @Author: codesky
 * @Description: 自定义redis配置
 */
@Component
@ConfigurationProperties(prefix = "redis")
public class RedisProperties {
    private String host;
    private int port;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
