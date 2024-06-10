package com.example.gateway.filter;


import com.example.gateway.security.JwtUtil;
import com.example.gateway.security.RouteValidator;
import com.google.common.net.HttpHeaders;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

@Component
public class AuthFilter extends AbstractGatewayFilterFactory<AuthFilter.Config>{
	
	private RouteValidator routeValidator;
	
	private JwtUtil jwtUtil;
	
	public AuthFilter(RouteValidator routeValidator, JwtUtil jwtUtil) {
		super(Config.class);
		this.routeValidator = routeValidator;
		this.jwtUtil =jwtUtil;
	}

	@Override
	public GatewayFilter apply(Config config) {
		return ((exchange, chain)->{
			
			if(routeValidator.isSecured.test(exchange.getRequest())) {
				if(!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
					throw new RuntimeException("Missing auth header");
				}
				String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
				
				String path = exchange.getRequest().getURI().getPath();

				String httpMethod = exchange.getRequest().getMethod().name();
				
				String[] parts = authHeader.split(" ");
				
				if(parts.length != 2 || !parts[0].equals("Bearer")) {
					throw new RuntimeException("Incorrect auth structure");
				}
				try {
					jwtUtil.validateJwtToken(parts[1], path, httpMethod);
				}catch(Exception e) {
					throw new RuntimeException("Unauthorized access");
				}
			}
			return chain.filter(exchange);
		});
	}

	
	public static class Config {
		
	}
}
