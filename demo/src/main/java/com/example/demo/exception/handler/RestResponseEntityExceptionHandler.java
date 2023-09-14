package com.example.demo.exception.handler;

import com.example.demo.controller.response.error.CustomErrorResponseBody;
import com.example.demo.exception.InvalidGenderException;
import com.example.demo.exception.UserAlreadyExistsException;
import com.example.demo.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({InvalidGenderException.class})
    public ResponseEntity<CustomErrorResponseBody> handleInvalidGenderException(InvalidGenderException ex, WebRequest webRequest) {
        return ResponseEntity.badRequest()
            .body(
                CustomErrorResponseBody.builder()
                    .status(HttpStatus.BAD_REQUEST.value())
                    .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                    .message(ex.getMessage())
                    .path(((ServletWebRequest) webRequest).getRequest().getRequestURI())
                    .build()
            );
    }

    @ExceptionHandler({UserNotFoundException.class})
    public ResponseEntity<CustomErrorResponseBody> handleUserNotFoundException(UserNotFoundException ex, WebRequest webRequest) {
        return ResponseEntity.badRequest()
            .body(
                CustomErrorResponseBody.builder()
                    .status(HttpStatus.NOT_FOUND.value())
                    .error(HttpStatus.NOT_FOUND.getReasonPhrase())
                    .message(ex.getMessage())
                    .path(((ServletWebRequest) webRequest).getRequest().getRequestURI())
                    .build()
            );
    }

    @ExceptionHandler({UserAlreadyExistsException.class})
    public ResponseEntity<CustomErrorResponseBody> handleUserAlreadyExistsException(UserAlreadyExistsException ex, WebRequest webRequest) {
        return ResponseEntity.badRequest()
            .body(
                CustomErrorResponseBody.builder()
                    .status(HttpStatus.CONFLICT.value())
                    .error(HttpStatus.CONFLICT.getReasonPhrase())
                    .message(ex.getMessage())
                    .path(((ServletWebRequest) webRequest).getRequest().getRequestURI())
                    .build()
            );
    }

}
