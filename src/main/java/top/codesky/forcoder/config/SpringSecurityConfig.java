package top.codesky.forcoder.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import top.codesky.forcoder.security.filter.CustomLoginAuthenticationFilter;

import java.util.Arrays;

@EnableWebSecurity(debug = false)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    private final AuthenticationSuccessHandler authenticationSuccessHandler;

    private final AuthenticationFailureHandler authenticationFailureHandler;

    private final AccessDeniedHandler accessDeniedHandler;

    private final AuthenticationEntryPoint authenticationEntryPoint;

    private final LogoutSuccessHandler logoutSuccessHandler;

    private final PasswordEncoder passwordEncoder;

    @Value("${http.cors.allowed-origin}")
    private String allowedOrigin;

    @Autowired
    public SpringSecurityConfig(@Qualifier("userDetailsServiceImpl") UserDetailsService userDetailsService, AuthenticationSuccessHandler authenticationSuccessHandler,
                                AuthenticationFailureHandler authenticationFailureHandler, AccessDeniedHandler accessDeniedHandler,
                                AuthenticationEntryPoint authenticationEntryPoint, LogoutSuccessHandler logoutSuccessHandler, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.authenticationSuccessHandler = authenticationSuccessHandler;
        this.authenticationFailureHandler = authenticationFailureHandler;
        this.accessDeniedHandler = accessDeniedHandler;
        this.authenticationEntryPoint = authenticationEntryPoint;
        this.logoutSuccessHandler = logoutSuccessHandler;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // http.formLogin();
        // 在拦截链中添加默认实现的UsernamePasswordAuthenticationFilter
        http.antMatcher("/api/**")
                .authorizeRequests()
                .antMatchers("/api/register").permitAll()
                .antMatchers("/api/admin/**").hasRole("ADMIN")
                .antMatchers("/api/user/**").hasRole("USER")
                .antMatchers("/api/**").authenticated();
        http.exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler)
                .authenticationEntryPoint(authenticationEntryPoint);

        http.addFilterAt(customLoginAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        //添加跨域请求配置
        http.cors();

        //设置注销拦截器
        http.logout()
                .logoutUrl("/api/logout")
                .logoutSuccessHandler(logoutSuccessHandler)
                .clearAuthentication(true)
                .invalidateHttpSession(true);

        http.csrf().ignoringAntMatchers("/**");
    }

    /**
     * 自定义登录拦截器
     *
     * @return 自定义登录拦截器
     * @throws Exception
     */
    private CustomLoginAuthenticationFilter customLoginAuthenticationFilter() throws Exception {
        CustomLoginAuthenticationFilter filter = new CustomLoginAuthenticationFilter();
        filter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
        filter.setAuthenticationFailureHandler(authenticationFailureHandler);
        filter.setAuthenticationManager(this.authenticationManager());
        filter.setFilterProcessesUrl("/api/login");
        return filter;
    }

    /**
     * 配置允许跨域请求拦截器
     *
     * @return
     */
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        //配置跨域请求
        CorsConfiguration corsConfig = new CorsConfiguration();

        corsConfig.setAllowedMethods(Arrays.asList(HttpMethod.POST.name(), HttpMethod.PUT.name(),
                HttpMethod.GET.name(), HttpMethod.DELETE.name(), HttpMethod.OPTIONS.name()));
        corsConfig.addAllowedOrigin(allowedOrigin);
        corsConfig.addAllowedHeader("content-type");
        corsConfig.setMaxAge(60L * 60L * 10L);
        corsConfig.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
        configSource.registerCorsConfiguration("/**", corsConfig);

        return configSource;
    }

    /**
     * 密码加salt-> hash编码器
     */
    @Bean
    public static BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
