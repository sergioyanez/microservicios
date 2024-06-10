package com.example.gateway.security;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
public class RouteValidator {

	//Endpoints públicos
	public static final List<String> openApiEndpoints = List.of(
			"/auth"
			);
	
	public Predicate<ServerHttpRequest> isSecured = 
			request -> openApiEndpoints
				.stream()
				.noneMatch(uri -> request.getURI().getPath().contains(uri));
}
