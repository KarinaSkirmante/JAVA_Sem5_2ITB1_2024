package lv.venta.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configurable
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfiguration{
	
	
	@Bean
	public UserDetailsService testUsers() {
		UserDetails u1Details = 
				User
				.builder()
				.username("admin")
				.password("123456")
				.authorities("ADMIN")
				.build();
		
		UserDetails u2Details = 
				User
				.builder()
				.username("karina")
				.password("654321")
				.authorities("USER")
				.build();
		
		UserDetails u3Details = 
				User
				.builder()
				.username("janis")
				.password("098765")
				.authorities("USER", "ADMIN")
				.build();
		
		return new InMemoryUserDetailsManager(u1Details, u2Details, u3Details);
		
	}
	
	@Bean
	public SecurityFilterChain configureEndpoints(HttpSecurity http) throws Exception {
		
			http
			.authorizeHttpRequests()
			.requestMatchers("/hello").permitAll()
			.requestMatchers("/hello/msg").permitAll()
			.requestMatchers("/product/test").hasAuthority("ADMIN")//tikai adminam
			.requestMatchers("/product/all").permitAll()
			.requestMatchers("/product/one?id=**").permitAll()
			.requestMatchers("/product/all/**").permitAll()
			.requestMatchers("/product/insert").hasAuthority("ADMIN")
			.requestMatchers("/product/update?id=**").hasAuthority("ADMIN")
			.requestMatchers("/product/delete?id=**").hasAuthority("ADMIN")
			.requestMatchers("/product/info/filter/**").hasAuthority("USER")
			.requestMatchers("/product/info/total").hasAuthority("ADMIN")
			.and()
			.formLogin().permitAll();


		return http.build();
		
		
		
	}
	
	
	

}
