package lt.asprogramuoju.service.customerbill.openapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.BasicAuth;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.Collections;
import java.util.function.Predicate;

/**
 * The swagger documentation can be viewed at {@code http://<host>:<port>/api/swagger-ui/index.html}
 * <p/>
 *
 * @author Pavel Vrublevskij
 */
@Configuration
@EnableSwagger2
class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("lt.asprogramuoju.service.customerbill.api.controller"))
                .paths(PathSelectors.any())
                // put below supported endpoints
                .paths(matchPathRegex(
                        "/api/customerBill/*"
                ))
                .build()
                .securityContexts(Collections.singletonList(securityContext()))
                .securitySchemes(Collections.singletonList(basicAuthScheme()));
    }

    private SecurityScheme basicAuthScheme() {
        return new BasicAuth("basicAuth");
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(Collections.singletonList(basicAuthReference()))
                .build();
    }

    private SecurityReference basicAuthReference() {
        return new SecurityReference("basicAuth", new AuthorizationScope[0]);
    }

    private static Predicate<String> matchPathRegex(final String... regexPaths) {
        return input -> Arrays.stream(regexPaths).anyMatch(input::matches);
    }
}
