package com.homefun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class SpringCloudMybatisApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudMybatisApplication.class, args);
	}
}
