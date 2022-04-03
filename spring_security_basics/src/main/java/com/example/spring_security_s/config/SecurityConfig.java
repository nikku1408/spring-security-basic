package com.example.spring_security_s.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.rsocket.EnableRSocketSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/*For in-memory example*/
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
		auth.inMemoryAuthentication().withUser("Ansh").password("ansh123")
				.authorities("Student");/* password not encoded in all the cases */
		auth.inMemoryAuthentication().withUser("Nikhil").password("nik123").authorities("Faculty");
		auth.inMemoryAuthentication().withUser("Rohini").password("rohini123").authorities("Management");
	}

	/* Authorization means role management */
//	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests()
//				/* For which url what access type */
//
//				.antMatchers("/home").permitAll().antMatchers("/welcome").authenticated().antMatchers("/student")
//				.hasAuthority("Student").antMatchers("/common").hasAnyAuthority("Student", "Faculty")
//				.antMatchers("/faculty").hasAuthority("Faculty").antMatchers("/management").hasAuthority("Management")
//				.antMatchers(HttpMethod.POST, "/register").permitAll().antMatchers(HttpMethod.GET, "/register")
//				.permitAll();
//		/* Here common is for common roles which are selected */
//	}

	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()

				/* URL-Access type */
				.antMatchers("/home").permitAll().antMatchers("/welcome").authenticated().antMatchers("/student")
				.hasAuthority("Student").antMatchers("/faculty").hasAuthority("Faculty").antMatchers("/management")
				.hasAuthority("Management")

				/* Login Form Details */
				.and().formLogin().defaultSuccessUrl("/welcome", true)

				/* Logout Details */
				.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))

				/* Exception Details */
				.and().exceptionHandling().accessDeniedPage("/denied");

	}
}
