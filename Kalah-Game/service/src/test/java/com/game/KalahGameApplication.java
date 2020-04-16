package com.game;

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
@EnableCaching
public class KalahGameApplication extends WebMvcConfigurerAdapter {

	
	/**
	 * Entry method - Main.
	 * */
	public static void main(String[] args) {
		SpringApplication.run(KalahGameApplication.class, args);
	}

	/**
	 * Method for registering {@link LoggingInterceptor}
	 * 
	 * @param registry {@link InterceptorRegistry}
	 * */
	
}
