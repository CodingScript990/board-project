package com.board.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;

// ThymeleafConfig Class => Thymeleaf Template 사용하기 위한 작업
@Configuration
public class ThymeleafConfig {
		// thymeleafTemplateResolver Bean 을 등록할려는데?
		// Spring type 이 SpringResourceTemplateResolver 로 치환해줄건데?
		// 기본 thymeleafTemplateResolver 값을 설정해주고,
		// defaultTemplateResolver 가 setUseDecoupledLogic 에서 thymeleaf3Properties 를 설정 한 값을 받아 주는 작업을 실행 해줌을 말함!
		@Bean
		public SpringResourceTemplateResolver thymeleafTemplateResolver(
						SpringResourceTemplateResolver defaultTemplateResolver,
						Thymeleaf3Properties thymeleaf3Properties
		) {
				defaultTemplateResolver.setUseDecoupledLogic(thymeleaf3Properties.isDecoupledLogic());

				return defaultTemplateResolver;
		}

		// @ConstructorBinding : SpringBoot 3.0 Version 이후로는 사용하지 않음[참고 - SpringBoot 공식문서]
		// SpringBoot.io = https://docs.spring.io/spring-boot/docs/current/api/org/springframework/boot/context/properties/ConstructorBinding.html
		// Thymeleaf3Properties => Properties 를 활용하여 언제든 User 가 Application.yml 을 통해 False, True 를 이용하여 Thymeleaf 를 쉽게 설정하기 위한 작업임
		@RequiredArgsConstructor
		@Getter
		@ConfigurationProperties("spring.thymeleaf3")
		public static class Thymeleaf3Properties {
				/**
				 * Use Thymeleaf 3 Decoupled Logic
				 */
				private final boolean decoupledLogic;

		}
}
