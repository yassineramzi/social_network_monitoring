package com.socialnetworkmonitoring.web.error.handler;


import com.socialnetworkmonitoring.exceptions.EntityAlreadyExistException;
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
		ApiError apiError = getErrorApiFromApiException(apiException);
		return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
	}

	@ExceptionHandler({ EntityAlreadyExistException.class })
	public ResponseEntity<Object> handleCompilatioError(final EntityAlreadyExistException entityAlreadyExistException)
	{
		ApiError apiError = getErrorApiFromEntityAlreadyExistException(entityAlreadyExistException);
		return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
	}

	private ApiError getErrorApiFromApiException(final ApiException apiException)
	{
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, apiException.getErrorName(), apiException.getEntity(), apiException.getMessage());
		return apiError;
	}

	private ApiError getErrorApiFromEntityAlreadyExistException(final EntityAlreadyExistException entityAlreadyExistException)
	{
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, entityAlreadyExistException.getErrorName(), "", entityAlreadyExistException.getMessage());
		return apiError;
	}
}
