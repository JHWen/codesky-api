package top.codesky.forcoder.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import top.codesky.forcoder.common.properties.StorageProperties;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @Date: 2019/5/10 12:38
 * @Author: codesky
 * @Description: MVC配置类
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private final Path rootLocation;

    @Autowired
    public WebMvcConfig(StorageProperties properties) {
        this.rootLocation = Paths.get(properties.getLocation());
    }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //配置静态资源映射器，映射到本地磁盘地址 file:///
        registry.addResourceHandler("/images/**")
                .addResourceLocations(rootLocation.toUri().toString());
    }
}
