package com.socialnetworkmonitoring.web.error.handler;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiError {
	@NonNull
	private HttpStatus status;
	
	@NonNull
	private String message;
	
	@NonNull
	private String entity;
	
	@NonNull
	private String error;
	
}
