package com.property.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.property.controller.result.Result;
import com.property.pojo.User;
import com.property.service.UserService;
import com.property.util.Md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

import java.io.PrintWriter;

/**
 * Spring Security Configuration
 * @author Chichiu Yeung
 * Created in 2018/12/12 19:29
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private UserService userService;
    private AuthenticationAccessDeniedHandler deniedHandler;
    private UrlAccessDecisionManager decisionManager;
    private LoginFailureHandler loginFailureHandler;

    @Value("${origin}")
    private String origin;

    @Autowired
    public SecurityConfig(UserService userService, AuthenticationAccessDeniedHandler deniedHandler, UrlAccessDecisionManager decisionManager, LoginFailureHandler loginFailureHandler) {
        this.userService = userService;
        this.deniedHandler = deniedHandler;
        this.decisionManager = decisionManager;
        this.loginFailureHandler = loginFailureHandler;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService)
                .passwordEncoder((new PasswordEncoder() {
                    @Override
                    public String encode(CharSequence rawPassword) {
                        return Md5.digest(rawPassword.toString());
                    }

                    @Override
                    public boolean matches(CharSequence rawPassword, String encodedPassword) {
                        return encodedPassword.toUpperCase().equals(Md5.digest(rawPassword.toString()));
                    }
                }));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O object) {
                        object.setAccessDecisionManager(decisionManager);
                        return object;
                    }
                })
                .and()
                .formLogin()
                .failureHandler(loginFailureHandler)
                .loginPage("/requestLogin")
                .loginProcessingUrl("/login")
                .usernameParameter("userName")
                .passwordParameter("userPwd")
                .successHandler((request, response, authentication) -> {
                    response.setCharacterEncoding("UTF-8");
                    response.addHeader("Access-Control-Allow-Origin", origin);
                    response.addHeader("Access-Control-Allow-Credentials", "true");
                    PrintWriter writer = response.getWriter();
                    ObjectMapper mapper = new ObjectMapper();
                    Result result = Result.success("登录成功");
                    // 返回信息清空密码
                    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                    user.setPassword(null);
                    result.add("user", user);
                    writer.write(mapper.writeValueAsString(result));
                    writer.flush();
                    writer.close();
                })
                .and()
                .logout().permitAll()
                .and().cors()
                .and().csrf().disable()
                .exceptionHandling().accessDeniedHandler(deniedHandler);
    }

//    /**
//     * 根据官方文档配置，解决跨域问题
//     * */
//    @Bean
//    CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOrigins(Arrays.asList("http://localhost", "http://120.79.93.191", "http://127.0.0.1"));
//        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "DELETE", "OPTIONS"));
//        configuration.setAllowCredentials(true);
//        configuration.setMaxAge(3600L);
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }

}
