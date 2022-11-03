package com.shoppingapp.userservice.model;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseForException {

	String messge;
	LocalDateTime timestamp;
	HttpStatus status;
}
