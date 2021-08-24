package com.socialnetworkmonitoring.web.error.handler;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.socialnetworkmonitoring.web.error.ApiException;


@ControllerAdvice
public class ApiErrorHandler extends ResponseEntityExceptionHandler
{
	@ExceptionHandler({ ApiException.class })
	public ResponseEntity<Object> handleCompilatioError(final ApiException apiException)
	{
		ApiError apiError = getErrorApi(apiException);
		return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
	}
	
	
	public ApiError getErrorApi(final ApiException apiException)
	{
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, apiException.getErrorName(), apiException.getEntity(), apiException.getMessage());
		return apiError;
	}
}
