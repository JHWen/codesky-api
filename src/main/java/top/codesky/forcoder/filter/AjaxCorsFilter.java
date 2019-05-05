package top.codesky.forcoder.filter;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.Collections;

/**
 * @Date: 2019/5/2 12:39
 * @Author: codesky
 * @Description: 配置允许跨域请求拦截器
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class AjaxCorsFilter extends CorsFilter {

    /**
     * Constructor accepting a {@link CorsConfigurationSource} used by the filter
     * to find the {@link CorsConfiguration} to use for each incoming request.
     *
     * @see UrlBasedCorsConfigurationSource
     */
    public AjaxCorsFilter() {
        super(configurationSource());
    }

    private static CorsConfigurationSource configurationSource() {
        //配置跨域请求
        CorsConfiguration corsConfig = new CorsConfiguration();

        corsConfig.setAllowedMethods(Arrays.asList(HttpMethod.POST.name(), HttpMethod.PUT.name(),
                HttpMethod.GET.name(), HttpMethod.DELETE.name(), HttpMethod.OPTIONS.name()));
        corsConfig.setAllowedOrigins(Collections.singletonList("http://localhost:8080"));
        corsConfig.addAllowedHeader("content-type");
        corsConfig.setMaxAge(60L * 60L * 10L);
        corsConfig.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
        configSource.registerCorsConfiguration("/**", corsConfig);

        return configSource;
    }
}
