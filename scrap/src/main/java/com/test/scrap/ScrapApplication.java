package com.test.scrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.test")
@EnableJpaRepositories("com.test")
@EntityScan("com.test,")
public class ScrapApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScrapApplication.class, args);
	}

}
