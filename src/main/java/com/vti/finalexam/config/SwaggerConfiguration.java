package com.vti.finalexam.config;

import static com.vti.finalexam.Constants.AUTHENTICATION.AUTHORIZATION_TOKEN;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.any())
            .paths(PathSelectors.any())
            .build()
            .securitySchemes(apiKeys())
            .securityContexts(Collections.singletonList(securityContext()));
    }

    private List<ApiKey> apiKeys() {
        return Collections.singletonList(
            new ApiKey(AUTHORIZATION_TOKEN, AUTHORIZATION_TOKEN, "header")
        );
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
            .securityReferences(defaultAuth())
            .forPaths(PathSelectors.any())
            .build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[]{
            new AuthorizationScope("global", "accessEverything")
        };
        return Collections.singletonList(
            new SecurityReference(AUTHORIZATION_TOKEN, authorizationScopes)
        );
    }
}
