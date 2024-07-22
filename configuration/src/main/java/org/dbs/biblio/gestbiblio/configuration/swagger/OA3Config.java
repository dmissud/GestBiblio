package org.dbs.biblio.gestbiblio.configuration.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(info = @Info(title = "GestBiblio API", version = "0.0.1", description = "Provide some api for Library management"))
@Configuration
public class OA3Config {
    @Bean
    public GroupedOpenApi userApi() {
        return GroupedOpenApi.builder()
                .group("Librarian")
                .pathsToMatch("/api/librarian/**")
                .build();
    }
}
