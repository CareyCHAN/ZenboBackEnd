package com.carey.zenboapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ZenboapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZenboapiApplication.class, args);
	}

}
