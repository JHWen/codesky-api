package top.codesky.forcoder.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import top.codesky.forcoder.security.CustomLoginAuthenticationFilter;
import top.codesky.forcoder.security.MyPasswordEncoder;

@EnableWebSecurity(debug = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    private final AuthenticationSuccessHandler authenticationSuccessHandler;

    private final AuthenticationFailureHandler authenticationFailureHandler;

    private final AccessDeniedHandler accessDeniedHandler;

    private final AuthenticationEntryPoint authenticationEntryPoint;

    private final LogoutSuccessHandler logoutSuccessHandler;

    @Autowired
    public SpringSecurityConfig(@Qualifier("userDetailsServiceImpl") UserDetailsService userDetailsService, AuthenticationSuccessHandler authenticationSuccessHandler,
                                AuthenticationFailureHandler authenticationFailureHandler, AccessDeniedHandler accessDeniedHandler,
                                AuthenticationEntryPoint authenticationEntryPoint, LogoutSuccessHandler logoutSuccessHandler) {
        this.userDetailsService = userDetailsService;
        this.authenticationSuccessHandler = authenticationSuccessHandler;
        this.authenticationFailureHandler = authenticationFailureHandler;
        this.accessDeniedHandler = accessDeniedHandler;
        this.authenticationEntryPoint = authenticationEntryPoint;
        this.logoutSuccessHandler = logoutSuccessHandler;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new MyPasswordEncoder());
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


}
