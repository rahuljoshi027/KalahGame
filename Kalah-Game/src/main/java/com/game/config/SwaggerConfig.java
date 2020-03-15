package com.game.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * This class contains swagger 2 configuration and creation of
 * Docket bean {@link Docket}
 * 
 * @author Rahul
 * */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	
	/**
	 * This method creates docket bean for swagger
	 * 
	 * @return Docket {@link Docket}
	 * */	
	@Bean
	public Docket swaggerConfiguration() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.paths(PathSelectors.any())
				.apis(RequestHandlerSelectors.basePackage("com.game"))
				.build()
				.apiInfo(apiDetails());
	}
	
	/**
	 * This method returns api information details required while creating docket {@link Docket} bean
	 * 
	 * @return ApiInfo {@link ApiInfo}
	 * */
	private ApiInfo apiDetails() {
		return new ApiInfo("KALAH Game Implementation",
							"Provides an implementation of KALAH",
							"1.0", 
							"Free to use", 
							new springfox.documentation.service.Contact("Rahul Joshi", "", "rahul.joshi027@gmail.com"),
							"Free license", 
							"NA", 
							 Collections.EMPTY_LIST);
	}
}
