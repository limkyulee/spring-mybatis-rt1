package com.kyuleelim.admincore.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EnvConfig {
    static {
        // .env 파일을 읽음
        Dotenv dotenv = Dotenv.configure()
                .ignoreIfMissing()
                .load();

        // 환경 변수 -> 시스템 프로퍼티로 설정
        System.setProperty("DB_USERNAME", dotenv.get("DB_USERNAME"));
        System.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD"));
    }
}
