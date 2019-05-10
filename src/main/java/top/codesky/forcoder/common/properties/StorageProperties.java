package top.codesky.forcoder.common.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Date: 2019/5/10 12:49
 * @Author: codesky
 * @Description: 自动读取application.properties中的文件存储相关的配置信息
 */
@Component
@ConfigurationProperties(prefix = "storage")
public class StorageProperties {
    private String location;
    private String rootUrl;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getRootUrl() {
        return rootUrl;
    }

    public void setRootUrl(String rootUrl) {
        this.rootUrl = rootUrl;
    }
}
