package com.jazieloliveira.wexbackendchallenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jdbc.repository.config.EnableJdbcAuditing;

@SpringBootApplication
@EnableJdbcAuditing
public class WexbackendchallengeApplication {

	public static void main(String[] args) {
		SpringApplication.run(WexbackendchallengeApplication.class, args);
	}

}
