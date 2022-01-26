package com.azurepoc.cdc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//@EnableScheduling
public class CDCApplication {

	public static void main(String[] args) {
		SpringApplication.run(CDCApplication.class, args);
	}

}
