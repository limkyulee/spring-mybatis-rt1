package com.kyuleelim.admincore;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.kyuleelim.admincore")
public class ApringMybatisRt1Application {

	public static void main(String[] args) {
		SpringApplication.run(ApringMybatisRt1Application.class, args);
	}

}
