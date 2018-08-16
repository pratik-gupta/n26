package com.n26.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.n26.model.ApiError;
/**
 * Generic class to customize and handle all rest exception response
 * @author Pratik
 *
 */
@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	private ResponseEntity<Object> errorResponse(HttpStatus status, HttpHeaders headers, String message) {
		final ApiError apiError = ApiError.builder().status(status.value()).detail(message).build();
		return ResponseEntity.status(status).headers(headers).body(apiError);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		String message = ex.getBindingResult().getFieldErrors().stream().map((fieldError) -> String
				.format("Error found for parameter '%s'; %s", fieldError.getField(), fieldError.getDefaultMessage()))
				.findFirst().orElse(ex.getMessage());
		return errorResponse(HttpStatus.BAD_REQUEST, new HttpHeaders(), message);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		if("com.fasterxml.jackson.databind.exc.InvalidFormatException".equals(ex.getCause().getClass().getName())) {
			return errorResponse(HttpStatus.UNPROCESSABLE_ENTITY, new HttpHeaders(), "Invalid field format");
		}
		return errorResponse(HttpStatus.BAD_REQUEST, new HttpHeaders(), "Invalid Json format");
	}

	@ExceptionHandler({ HttpStatusCodeException.class })
	final ResponseEntity<Object> handleHttpException(Exception ex) {

		final HttpStatus status;final String message;
		if (ex instanceof HttpStatusCodeException) {
			status = ((HttpStatusCodeException) ex).getStatusCode();
			message = ((HttpStatusCodeException) ex).getStatusText();
		} else {
			message = ex.getMessage();
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return errorResponse(status, new HttpHeaders(), message);
	}
}