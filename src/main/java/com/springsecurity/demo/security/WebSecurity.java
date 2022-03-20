package com.springsecurity.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.springsecurity.demo.filter.JwtAuthorizationFilter;
import com.springsecurity.demo.service.MyUserDetailService;

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

	@Autowired
    private MyUserDetailService myUserDetailService;
	@Autowired
    private JwtAuthorizationFilter jwtAuthorizationFilter;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		
		super.configure(auth);
		/*auth.inMemoryAuthentication()
		.withUser("user1").password("123").roles("APPRENTICE")
        .and()
        .withUser("user2").password("123").roles("SENSEI");
		*/
		auth.userDetailsService(myUserDetailService);
	}
	@Bean
    public PasswordEncoder getPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		/* configuracion base tipo 2
		http.csrf().disable()
		.authorizeHttpRequests().antMatchers("/login").permitAll()
		.anyRequest().authenticated();
		*/
		//super.configure(http);
		/* configuracion base tipo 1
		 * http.authorizeRequests()
        .antMatchers("/sensei").hasRole("SENSEI")
        .antMatchers("/apprentice").hasRole("APPRENTICE")
        .antMatchers("/").permitAll()
        .and().formLogin();*/
		
		 http
         .csrf().disable()
         .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
         .and()
         .authorizeRequests().antMatchers("/login").permitAll()
         .anyRequest().authenticated()
         .and().addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);

	}
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManagerBean();
	}
	

}
