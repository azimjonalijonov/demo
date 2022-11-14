package com.example.demo.config;

import com.example.demo.security.JWTProvider;
import com.example.demo.security.JwtConfigure;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;


@Configuration
@EnableWebSecurity
public class SpringSecurity extends WebSecurityConfigurerAdapter {
    private final JWTProvider jwtProvider;
    private final UserDetailsService userDetailsService;

    public SpringSecurity(JWTProvider jwtProvider, @Lazy UserDetailsService userDetailsService) {
        this.jwtProvider = jwtProvider;
        this.userDetailsService = userDetailsService;
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//    }
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
                .antMatchers("/api/posts/").hasAnyRole("USER","ADMIN")
                .antMatchers("/api/register").permitAll()
                .antMatchers("/api/aut").permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic()
                .and()
                .apply(securityConfigurerAdapter());
    }
private JwtConfigure securityConfigurerAdapter(){return new JwtConfigure(jwtProvider);}

}
