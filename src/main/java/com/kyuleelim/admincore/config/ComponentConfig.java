package com.kyuleelim.admincore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * ===========================================
 * Project      : admin-core-api
 * File Name    : ComponentConfig.java
 * Author       : limkyulee
 * Created Date : 2025. 5. 3. 오후 8:17
 * Updated Date : 2025. 5. 3. 오후 8:17
 * Description  : @Bean Exception Config
 * ===========================================
 */
@Configuration
@ComponentScan(basePackages = "com.kyuleelim.admincore")
public class ComponentConfig {

    @Bean
    public ExceptionInfoConfig getExceptionInfoConfig() {
        return new ExceptionInfoConfig();
    }
}
