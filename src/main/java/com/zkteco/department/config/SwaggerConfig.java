package com.zkteco.department.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static springfox.documentation.builders.PathSelectors.regex;

@EnableSwagger2
@Configuration
public class SwaggerConfig {
   @Bean
	public Docket documentApi() {
		return new Docket(DocumentationType.SWAGGER_2)
		.select()
		.apis(RequestHandlerSelectors.basePackage("com.zkteco.department"))
		.paths(regex("/api/v1/department.*"))
		//.paths(PathSelectors.any())
		.build()
		.apiInfo(metaInfo());
	}


private ApiInfo metaInfo() {
	ApiInfo  apiInfo = new ApiInfo("Department", "Spring boot Project", "1.0", "service.doc", new Contact("Rajesh", "https://www.zkteco.in", "rajesh@zkteco.in"), "Apache2.0 ","lice");
	return apiInfo;
}
}
