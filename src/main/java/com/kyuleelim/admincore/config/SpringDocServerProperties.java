package com.kyuleelim.admincore.config;

import io.swagger.v3.oas.models.servers.Server;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author limkyulee
 * @version 1.0 2025.4.28
 * @see SpringDocServerProperties
 */
@ConfigurationProperties(prefix = "springdoc")
@Component
@Getter
@Setter
public class SpringDocServerProperties {
    private List<Server> servers = new ArrayList<>();
}
