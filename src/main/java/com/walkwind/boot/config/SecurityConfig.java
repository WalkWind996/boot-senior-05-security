package com.walkwind.boot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * @Author walkwind
 * @Description
 * @Date 2020-4-27-14:56
 **/
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                //定制请求的授权规则
                .antMatchers("/").permitAll()
                .antMatchers("/level1/**").hasRole("VIP1")
                .antMatchers("/level2/**").hasRole("VIP2")
                .antMatchers("/level3/**").hasRole("VIP3")
                //开启自动配置的登陆功能，效果，如果没有登陆，没有权限就会来到登陆页面
                //这个formLogin().permitAll()方法允许所有用户基于表单登录访问/login这个page。
                .and().formLogin().loginPage("/userLogin").loginProcessingUrl("/userLogin/form").usernameParameter("username").passwordParameter("password").permitAll()
                .and().rememberMe().rememberMeParameter("rememberMe")
                .and().logout().logoutSuccessUrl("/");
        //会产生一个hiden标签里面有安全相关的验证:防止请求伪造
        http .csrf().disable();
    }

    @Bean
    @Override
    //使用内存中的用户
    public UserDetailsService userDetailsService() {
        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username("zhangsan")
                        .password("123456")
                        .roles("VIP1","VIP2")
                        .build();
        UserDetails user1 =
                User.withDefaultPasswordEncoder()
                        .username("lisi")
                        .password("123456")
                        .roles("VIP3","VIP2")
                        .build();
        return new InMemoryUserDetailsManager(user,user1);
    }
}