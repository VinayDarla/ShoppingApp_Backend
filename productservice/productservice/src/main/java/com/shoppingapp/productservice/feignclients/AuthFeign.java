package com.shoppingapp.productservice.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.shoppingapp.productservice.model.AuthResponse;

@FeignClient(name = "${authservice.client.name}", 
url = "${authservice.client.url}")
public interface AuthFeign {

				/**
				 * 
				 * @param token
				 * @return
				 */
	@GetMapping(value = "/api/v1.0/Shopping/adminvalidate")
	public ResponseEntity<AuthResponse> getAdminValidity(@RequestHeader("Authorization") final String token);
	@GetMapping(value = "/api/v1.0/Shopping/validate")
	public ResponseEntity<AuthResponse> getValidity(@RequestHeader("Authorization") final String token);
}

