package com.example.spring_security_s.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.rsocket.EnableRSocketSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableRSocketSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private BCryptPasswordEncoder encoder;

	/*
	 * Authentication means read username and password and validating 1. In memory
	 * authentication - store data in ram and validate 2. jdbc auth - store data in
	 * db and validate but works with sql queries 3. user detail service - store
	 * data in db and validate but works with orm (hql, jpa) queries
	 */
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

	}

	/* Authorization means role management */
	protected void configure(HttpSecurity http) throws Exception {

		super.configure(http);
	}
}
