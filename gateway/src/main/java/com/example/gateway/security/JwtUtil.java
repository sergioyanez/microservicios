package com.example.gateway.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Component
public class JwtUtil {
	
	@Value("${jwt.secret}")
	private String secret;

	public boolean validateJwtToken(String token, String path, String httpMethod) {
		Claims claims = Jwts.parser()
				.setSigningKey(this.secret)
				.parseClaimsJws(token)
				.getBody();

		boolean hasRole = false;
System.out.println("--------------------------------      "+path +"     -----------------------------------------------------------");
		if (path.contains("/users") ) {

			if ("GET".equalsIgnoreCase(httpMethod)) {
				hasRole = hasRole(claims, "ADMIN,USER");
			} else {
				hasRole = hasRole(claims, "ADMIN");
			}
		} else {
			hasRole = hasRole(claims, "ADMIN");
		}

		boolean isTokenExpired = claims.getExpiration().before(new Date());

		return (!isTokenExpired && hasRole);
	}

	
	private boolean hasRole(Claims claims, String role) {
		String roles = (String)claims.get("roles");
		
		List<String> rolesList = Arrays.asList(roles.split(","));
		
		for(String s: rolesList) {
			if(role.contains(s.toUpperCase())) {
				return true;
			}
		}
		throw new RuntimeException("Incorrect role");
	}
}
