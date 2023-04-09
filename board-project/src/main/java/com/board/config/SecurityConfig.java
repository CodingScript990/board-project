package com.board.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

// SecurityConfig Class => Security 를 사용하기 위한 작업
@Configuration
public class SecurityConfig {
		// Bean => SecurityFilterChain Constructor add => HttpSecurity type variable http[request]
		// http 는 authorizeHttpRequests(요청에 대한 권한을 지정하며),
		// anyRequest(요청을 할거야)
		// permitAll(접근을 허용인지 or 제한을 하는지)
		// formLogin(FormLogin 을 통해서 로그인 페이지를 View 에 보여줄거야 / Form Login 이 동작함[API => login])
		// and.build(그리고 생성해줌!)
		@Bean
		public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
				return http
								.authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
								.formLogin()
								.and().build();
		}
}
