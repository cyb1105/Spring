package com.cyb.myspringboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
@Profile("prod")
@Configuration
public class ProductionConfig {

	@Bean
	public String hello() {
		return "운영환경에서 사용되는 hello Bean";
	}
}
