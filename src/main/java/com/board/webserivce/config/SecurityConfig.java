package com.board.webserivce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.board.webserivce.service.UserSecurityService;

import lombok.AllArgsConstructor;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	private UserSecurityService userSecurityService;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		// static 폴더 하위 목록은 인증 무시하기
		web.ignoring().antMatchers("/css/**", "/js/**", "/img/**");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// 페이지 접근 권한에 대한 설정
		http.authorizeRequests()
			.antMatchers("/hello").hasRole("USER")
			.antMatchers("/**").permitAll()
		.and()	// 로그인 설정
			.formLogin()
			.loginPage("/")
			.loginProcessingUrl("/user/login")
			.defaultSuccessUrl("/hello", true)
			.failureUrl("/login/error")
			.permitAll()
		.and()	// 로그아웃 설정
			.logout()
			.logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
			.logoutSuccessUrl("/")
			.invalidateHttpSession(true)
		.and() // 403 예외처리 설정
			.exceptionHandling().accessDeniedPage("/login/error");
	}
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userSecurityService).passwordEncoder(passwordEncoder());
	}
}
