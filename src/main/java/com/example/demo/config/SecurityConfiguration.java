package com.example.demo.config;

import com.example.demo.security.JWTProvider;
import com.example.demo.security.JwtConfigure;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
 private  final JWTProvider jwtProvider;

    public SecurityConfiguration(JWTProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }


    // @Override
    //protected void configure(AuthenticationManagerBuilder auth) throws Exception {
      //  auth
         //       . inMemoryAuthentication()
       //.withUser("salm").password(passwordEncoder().encode("1234")).roles("a");
        // .userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
  //  }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                csrf()
                .disable()
                .headers().frameOptions().disable()
                .and()

                . authorizeRequests()
                .antMatchers("/api/post").hasRole("Role_Admin")
                .antMatchers("/api/posts/pageable").hasRole("ADMIN")
                .antMatchers("/api/register").hasRole("ADMIN")
                .antMatchers("/api/autt").permitAll()
                .anyRequest().
                authenticated()
                .and()
                .httpBasic().and().apply(securityConfigureAdapter());
    }
public JwtConfigure securityConfigureAdapter(){
        return new JwtConfigure(jwtProvider);
}

}