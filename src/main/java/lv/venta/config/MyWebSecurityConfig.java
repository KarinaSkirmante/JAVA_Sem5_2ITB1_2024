package lv.venta.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lv.venta.service.impl.MyUserDetailsServiceImpl;


//TODO 
//izveidot MyUser klasi
//izveidot MyAuthority klasi
//uztaisīt sasaisti ManyToMany
//izveidot repozitorijus
//izveidot COmandlineRunener funkcijā vismaz divus lietotājus un 2 autoritātes
//un saglabāt repo



@Configuration
@EnableWebSecurity
public class MyWebSecurityConfig{
	
	
	
	@Bean
	public MyUserDetailsServiceImpl userDetailsManager() {
		return new MyUserDetailsServiceImpl();
	}
	
	@Bean
	public DaoAuthenticationProvider linkWithDB() {
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsManager());
		provider.setPasswordEncoder(encoder);

		
		return provider;
	}
	
	
	
	@Bean
	public SecurityFilterChain configureEndpoints(HttpSecurity http) throws Exception {
		
			http
			.authorizeHttpRequests(auth -> auth
			.requestMatchers("/hello").permitAll()
			.requestMatchers("/hello/msg").permitAll()
			.requestMatchers("/product/test").hasAuthority("ADMIN")//tikai adminam
			.requestMatchers("/product/all").permitAll()
			.requestMatchers("/product/one**").permitAll()
			.requestMatchers("/product/all/**").permitAll()
			.requestMatchers("/product/insert").hasAuthority("ADMIN")
			.requestMatchers("/product/update**").hasAuthority("ADMIN")
			.requestMatchers("/product/delete**").hasAuthority("ADMIN")
			.requestMatchers("/product/info/filter/**").hasAuthority("USER")
			.requestMatchers("/product/info/total").hasAuthority("ADMIN")
			.requestMatchers("/h2-console/**").hasAuthority("ADMIN")
			);
			
			http.formLogin(form -> form.permitAll());
			http.csrf(csrf-> csrf.disable());
			http.headers(frame-> frame.frameOptions(option->option.disable()));
			
		return http.build();
	
	}
	
	
	

}
