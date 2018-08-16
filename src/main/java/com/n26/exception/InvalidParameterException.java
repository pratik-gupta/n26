package com.n26.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;
/**
 * Custom exception to handle invalid parameter exception.
 * @author Pratik
 *
 */
public class InvalidParameterException extends HttpStatusCodeException {
    public InvalidParameterException(HttpStatus status, String message) {
        super(status, message);
    }
}
