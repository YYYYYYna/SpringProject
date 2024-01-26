package com.sist.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration

//ÀÌ·§´Âµ¥?<context:component-scan base-package="com.sist.*"/>
@ComponentScan(basePackages="com.sist.*")
public class CustomerConfig {
	
}
