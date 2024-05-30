package com.example.microserviciocar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MicroservicioCarApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicioCarApplication.class, args);
	}

}
