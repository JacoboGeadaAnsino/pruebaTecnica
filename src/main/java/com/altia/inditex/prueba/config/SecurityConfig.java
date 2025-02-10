package com.altia.inditex.prueba.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http.sessionManagement((session) -> {
			session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		});		
		http.csrf(AbstractHttpConfigurer::disable);
		http.authorizeHttpRequests(
				(authorizeHttpRequests) -> 
						authorizeHttpRequests		
						.requestMatchers(HttpMethod.GET, "/api/consulta/**").permitAll()
						.requestMatchers("/swagger-ui/**", "/v3/api-docs*/**").permitAll()						
		);
		return http.build();

	}
}
