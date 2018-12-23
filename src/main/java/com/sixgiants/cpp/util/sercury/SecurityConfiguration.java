package com.sixgiants.cpp.util.sercury;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private SecurityAuthenticationProvider securityAuthenticationProvider;

    @Autowired
    private AjaxAuthSuccessHandler ajaxAuthSuccessHandler;
    @Autowired
    private AjaxAuthFailHandler ajaxAuthFailHandler;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(securityAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                // 完全可以访问
                .antMatchers("/css/**","/js/**","/vendors/**","/image/**","/hanzhen.ttf","/upload/**","/static/**").permitAll()
                .antMatchers("/visitor/login/form","/user/**","/user/register","/user/register_form","/user/login_action","/orders","/length","/settlement","/sex","/salary","/jobtype","/release","/classification","/orderdetail","/detail_action","/save_order").permitAll()
                //要求用户进行身份验证，并且必须与USER角色相关联
                .and()
                .authorizeRequests()
                .antMatchers("/some/**").hasRole("USER")
                //.antMatchers("/templates/**").hasRole("ADMIN")
                    .and()
                //自定义登录页面和失败URL启用基于表单的身份验证
                .formLogin()
                    .loginPage("/user/login")
                    .loginProcessingUrl("/visitor/login/form")
                    .usernameParameter("username")
                    .passwordParameter("password")
                    .successHandler(ajaxAuthSuccessHandler)
                    .failureHandler(ajaxAuthFailHandler)
                    .permitAll()
                .and()
                .logout()
                    .logoutUrl("logout")
                    .logoutSuccessUrl("/user/login")
                    .invalidateHttpSession(true)
                    .and()
                .csrf().disable();
        super.configure(http);
    }
}
