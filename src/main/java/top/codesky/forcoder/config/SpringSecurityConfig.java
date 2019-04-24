package top.codesky.forcoder.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import top.codesky.forcoder.security.CustomLoginAuthenticationFilter;
import top.codesky.forcoder.security.MyPasswordEncoder;
import top.codesky.forcoder.security.handler.MyAccessDeniedHandler;
import top.codesky.forcoder.security.handler.MyAuthenticationHandler;

@EnableWebSecurity(debug = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    private final MyAuthenticationHandler myAuthenticationHandler;

    private final MyAccessDeniedHandler myAccessDeniedHandler;

    @Autowired
    public SpringSecurityConfig(@Qualifier("userDetailsServiceImpl") UserDetailsService userDetailsService, MyAuthenticationHandler myAuthenticationHandler, MyAccessDeniedHandler myAccessDeniedHandler) {
        this.userDetailsService = userDetailsService;
        this.myAuthenticationHandler = myAuthenticationHandler;
        this.myAccessDeniedHandler = myAccessDeniedHandler;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new MyPasswordEncoder());
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // http.formLogin();
        // 在拦截链中添加默认实现的UsernamePasswordAuthenticationFilter
        http.antMatcher("/api/**").
                authorizeRequests()
                .antMatchers("/api/register").permitAll()
                .antMatchers("/api/admin/**").hasRole("ADMIN")
                .antMatchers("/api/user/**").hasRole("USER")
                .antMatchers("/api/**").authenticated();
        http.exceptionHandling()
                .accessDeniedHandler(myAccessDeniedHandler)
                .authenticationEntryPoint(myAccessDeniedHandler);

        http.addFilterAt(customLoginAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        http.csrf().ignoringAntMatchers("/**");
    }

    private CustomLoginAuthenticationFilter customLoginAuthenticationFilter() throws Exception {
        CustomLoginAuthenticationFilter filter = new CustomLoginAuthenticationFilter();
        filter.setAuthenticationSuccessHandler(myAuthenticationHandler);
        filter.setAuthenticationFailureHandler(myAuthenticationHandler);
        filter.setAuthenticationManager(this.authenticationManager());
        filter.setFilterProcessesUrl("/api/login");
        return filter;
    }


}
