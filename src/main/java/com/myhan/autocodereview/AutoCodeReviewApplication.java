package com.myhan.autocodereview;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class AutoCodeReviewApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutoCodeReviewApplication.class, args);
	}

}
