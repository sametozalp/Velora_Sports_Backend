package com.ozalp.Velora.Sports.security;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {

    public HttpSecurity httpSecurity(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        return httpSecurity;
    }
}
