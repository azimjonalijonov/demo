package com.example.demo.rest;

import com.example.demo.rest.vm.VmModel;
import org.springframework.context.annotation.Bean;


import com.example.demo.security.JWTProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
@RestController
@RequestMapping("/api")
public class JWTController {
    private final JWTProvider jwtProvider ;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    public JWTController(JWTProvider jwtProvider, AuthenticationManagerBuilder authenticationManagerBuilder) {
        this.jwtProvider = jwtProvider;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
    }
    @SuppressWarnings("SpellCheckingInspection")
    @PostMapping("/aut")
    public ResponseEntity<String> authorize(@Valid @RequestBody VmModel vmModel){
        UsernamePasswordAuthenticationToken authenticationToken =new UsernamePasswordAuthenticationToken(vmModel.getUserName() ,vmModel.getPassword());
        Authentication authentication =authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt =jwtProvider.createToken(vmModel.getUserName(), authentication);
        HttpHeaders httpHeaders =new HttpHeaders();
        httpHeaders.add("Authorization","BearToken "+jwt);
        return new ResponseEntity(new JwtToken(jwt), httpHeaders, HttpStatus.OK);
    }
    static class JwtToken{
        private String  idToken;

        public JwtToken(String idToken) {
            this.idToken = idToken;
        }

        public String getIdToken() {
            return idToken;
        }

        public void setIdToken(String idToken) {
            this.idToken = idToken;
        }
    }
}
