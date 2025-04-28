package com.kyuleelim.admincore.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author limkyulee
 * @version 1.0 2025.4.28
 * @see SwaggerConfig
 */
@Configuration
public class SwaggerConfig {

    private final SpringDocServerProperties serverProperties;

    public SwaggerConfig(SpringDocServerProperties serverProperties) {
        this.serverProperties = serverProperties;
    }

    @Bean
    public OpenAPI openAPI() {
        List<Server> servers = serverProperties.getServers();

        return new OpenAPI()
            .info(new Info()
                .title("KYULEELIM RT1")
                .description("Spring Mybatis REST API")
                .version("1.0")
                .contact(new Contact()
                        .name("kyuleelim")
                        .email("kyuleelim@gmail.com")
                        .url("https://github.com/limkyulee")
                )
                .license(new License()
                        .name("Apache 2.0")
                        .url("http://springdoc.org")
                )
            )
            .servers(servers);
    }
}
