package br.edu.ifsp.spo.clientdataprocessor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.annotations.ApiIgnore;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Configuration
@SpringBootApplication

public class ClientDataProcessorApplication {
	@Value("${trabalho.swagger.path}")
	private String swaggerPath;

	public static void main(String[] args) {
		SpringApplication.run(ClientDataProcessorApplication.class, args);
	}

	@Bean
	public Docket allApi() {
		ParameterBuilder aParameterBuilder = new ParameterBuilder();
		aParameterBuilder.name("Authorization").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
		List<Parameter> aParameters = new ArrayList<Parameter>();
		aParameters.add(aParameterBuilder.build());

		Set<String> protocols = new HashSet<>();
		protocols.add("http");
		protocols.add("https");

		return new Docket(DocumentationType.SWAGGER_2).host(swaggerPath)
				.groupName("All")
				.apiInfo(apiInfo())
				.select()
				.paths(PathSelectors.any())
				.build()
				.protocols(protocols)
				.ignoredParameterTypes(ApiIgnore.class)
				.enableUrlTemplating(true).globalOperationParameters(aParameters);

	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("API")
				.description("Trabalho LP2A4")
				.termsOfServiceUrl("http://localhost:8080")
				.license("")
				.licenseUrl("http://localhost:8080")
				.version("1.0")
				.build();

	}

	private Parameter apiParameter() {
		return new ParameterBuilder()
				.name("Authorization")
				.description("Authorization header")
				.parameterType("header")
				.required(false)
				.build();
	}
}
