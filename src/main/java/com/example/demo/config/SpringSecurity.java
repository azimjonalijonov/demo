package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;



@Configuration
@EnableWebSecurity
public class SpringSecurity extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("a").password(passwordEncoder().encode("1234")).roles("ADMIN")
                .and()
                .withUser("ab").password(passwordEncoder().encode("1234")).roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .headers()
                .frameOptions()
                .disable()
                .and()
                .authorizeRequests()
                .antMatchers("/api/posts/pageable/**").hasRole("ADMIN")
                .antMatchers("/api/posts/").hasRole("USER")
                .antMatchers("/api/register").permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic();
    }
    @Bean
    PasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }

}
