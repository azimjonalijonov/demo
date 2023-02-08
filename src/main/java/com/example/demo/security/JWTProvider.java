package com.example.demo.security;

import com.example.demo.Entity.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.xml.crypto.Data;
import java.util.Base64;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

import static io.jsonwebtoken.Jwts.parser;

@Component
public class JWTProvider {
    private final UserDetailsService userDetailsService;
    @Value("${jwt.token.secret}")
    private String secret;
    @Value("${jwt.token.validity}")
    private Long validtyMilliSecunds;

    public JWTProvider(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    protected void init() {
        this.secret = Base64.getEncoder().encodeToString(secret.getBytes());
    }

    public String createToken(String userName, Authentication authentication) {

        Claims claims = Jwts.claims().setSubject(userName);
        String roles = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
        claims.put("roles", roles);
        Date date = new Date();
        Date validity = new Date(date.getTime() + validtyMilliSecunds);
        return Jwts.builder().setClaims(claims)
                .setIssuedAt(date)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, this.secret)
                .compact();
    }

    public boolean validateToken(String jwt) {
        try {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(jwt);
            if (claimsJws.getBody().getExpiration().before(new Date())) {
                return false;
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        return true;
    }
    public Authentication getAuthentication(String token){
        UserDetails userDetails =userDetailsService.loadUserByUsername(getUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails,"", userDetails.getAuthorities());
    }
    private String getUsername(String toke){
        return Jwts.parser().setSigningKey(this.secret).parseClaimsJws(toke).getBody().getSubject();
    }
        @Bean
        PasswordEncoder passwordEncoder () {
            return new BCryptPasswordEncoder();
        }
    }
