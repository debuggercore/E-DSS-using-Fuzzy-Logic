package com.service.impl;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.service.inter.ElectiveCourseServiceIF;

@Configuration
public class AppConfig {
	@Bean
	public ElectiveCourseServiceIF getElectiveCourseServiceIF() {
		return new ElectiveCourseServiceImpl();
	}
}