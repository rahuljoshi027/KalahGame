package com.game;

import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 
 * Main SpringBootApplication class
 * 
 * contains main method
 * contains addInterceptor method for registering {@link LoggingInterceptor} instance
 * 
 * @author Rahul
 * 
 * */
@SpringBootApplication
public class KalahGameApplicationTest extends WebMvcConfigurerAdapter {

	@Test
	void contextLoads() {
	}

	
}
