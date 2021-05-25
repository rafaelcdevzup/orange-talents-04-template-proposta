package com.rafazup.bootcamp.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;

@Configuration
public class SecurityConfigurerAdapter  extends WebSecurityConfigurerAdapter{
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests(authorizeRequests ->
        authorizeRequests
            .antMatchers(HttpMethod.GET, "/actuator/**").permitAll()
            .antMatchers(HttpMethod.GET, "/api/propostas/**").hasAuthority("SCOPE_propostas")
            .antMatchers(HttpMethod.GET, "/api/biometrias/**").hasAuthority("SCOPE_propostas")
            .antMatchers(HttpMethod.POST, "/api/biometrias/**").hasAuthority("SCOPE_propostas")
            .antMatchers(HttpMethod.POST, "/api/propostas/**").hasAuthority("SCOPE_propostas")
            .anyRequest().authenticated()
)
        .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
    }
}