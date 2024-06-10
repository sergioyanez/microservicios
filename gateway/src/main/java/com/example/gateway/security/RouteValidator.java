package com.example.gateway.security;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
public class RouteValidator {

	//endpoints públicos
	public static final List<String> openApiEndpoints = List.of(
			"/auth"//,
//			"/v2/api-docs",
//			"/v3/api-docs",
//			"/v3/api-docs/**",
//			"/swagger-resources",
//			"/swagger-resources/**",
//			"/configuration/ui",
//			"/configuration/security",
//			"/swagger-ui/**",
//			"/webjars/**",
//			"/swagger-ui.html"
			);
	
	public Predicate<ServerHttpRequest> isSecured = 
			request -> openApiEndpoints
				.stream()
				.noneMatch(uri -> request.getURI().getPath().contains(uri));
}
