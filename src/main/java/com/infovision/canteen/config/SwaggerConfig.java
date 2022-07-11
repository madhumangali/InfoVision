package com.infovision.canteen.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
	
	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(getApiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.infovision.canteen"))
				.paths(PathSelectors.any())
				.build();
	}

	private ApiInfo getApiInfo() {
		return new ApiInfoBuilder()
				.title("InfoEat Application")
				.description("Documentation for InfoEat Application")
				.version("1.0.0")
				.build();
	}

}
