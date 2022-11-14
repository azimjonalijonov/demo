package com.example.demo.security;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Base64;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class JWTProvider {
    private final UserDetailsService userDetailsService;
    @Value("${jwt.token.secret}")
    private String secret;
    @Value("${jwt.token.validity}")
    private  Long validtyMilliSecunds;

    public JWTProvider(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @PostConstruct
    protected void init(){
        this.secret = Base64.getEncoder().encodeToString(secret.getBytes());
    }

 public  String createToken(String userName , Authentication authentication){
     String roles =authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));
     Claims claims= Jwts.claims().setSubject(userName);
     claims.put("roles",roles);
     Date now =new Date();
     Date validity =new Date(now.getTime()+validtyMilliSecunds);
     return Jwts.builder().
             setClaims(claims)
             .setIssuedAt(now).
              setExpiration(validity)
             .signWith(SignatureAlgorithm.HS256,this.secret)
             .compact();
 }
 public  Authentication getAuthentication(String token){
     UserDetails userDetails =userDetailsService.loadUserByUsername(getUsername(token));
   return new UsernamePasswordAuthenticationToken(userDetails,"",userDetails.getAuthorities());
 }
 private String getUsername(String token){
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();

 }
public  boolean validateToken(String token){
        try {
            Jws<Claims> claimsJws =Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
            if (claimsJws.getBody().getExpiration().before(new Date())){
                return false;
            }
        }catch (IllegalArgumentException e){
            e.printStackTrace();

        }
        return true;
}


    @Bean
    PasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }



}