package com.example.microserviciouser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MicroservicioUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicioUserApplication.class, args);
	}

}
