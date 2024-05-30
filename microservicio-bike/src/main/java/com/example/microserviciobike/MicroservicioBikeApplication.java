package com.example.microserviciobike;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MicroservicioBikeApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicioBikeApplication.class, args);
	}

}
