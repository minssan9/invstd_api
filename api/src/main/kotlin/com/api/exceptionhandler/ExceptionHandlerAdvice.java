package com.api.exceptionhandler;

import com.core.component.exception.CommonException;
import com.core.component.exception.CommonExceptionType;
import java.nio.file.AccessDeniedException;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

// TODO : For the admin, we need to add a controller advice with a corresponded exception.
@Slf4j
@RestControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleException(Exception e) {
        log.error(e.getMessage(), e);
        final ExceptionResponse response = new ExceptionResponse(
            CommonExceptionType.INTERNAL_SERVER_ERROR, e);
        return ResponseEntity.internalServerError().body(response);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionResponse> handleRuntimeException(RuntimeException e) {
        log.error(e.getMessage(), e);
        return ResponseEntity.internalServerError().body(
                new ExceptionResponse(CommonExceptionType.INTERNAL_SERVER_ERROR, e));
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<ExceptionResponse> handleExpiredJwtException(ExpiredJwtException e) {
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(
                new ExceptionResponse(CommonExceptionType.INTERNAL_SERVER_ERROR, e),
                HttpStatus.UNAUTHORIZED
        );
    }

    @ExceptionHandler(InvalidDataAccessResourceUsageException.class)
    public ResponseEntity<ExceptionResponse> handleRuntimeException(InvalidDataAccessResourceUsageException e) {
        log.error(e.getMessage(), e);
        final ExceptionResponse response = new ExceptionResponse(CommonExceptionType.INTERNAL_SERVER_ERROR, e.getCause().getCause().getMessage());

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CommonException.class)
    public ResponseEntity<ExceptionResponse> handleCustomInternalException(CommonException e) {
        log.error(e.getMessage(), e);
        ResponseEntity responseEntity;
        switch (e.getType()){
            case INVALID_ACCESS_TOKEN:
                responseEntity = new ResponseEntity<>(new ExceptionResponse(e), HttpStatus.UNAUTHORIZED); break;
            case NONEXIST_DATA_EXCEPTION_MSG:
                responseEntity = new ResponseEntity<>(new ExceptionResponse(e), BAD_REQUEST); break;
            case INVALID_REQUEST:
                responseEntity = ResponseEntity.badRequest().body(e); break;
            default:
                responseEntity = ResponseEntity.internalServerError().body(e); break;
        }
        return responseEntity;
    }

    // When @RequestBody (HttpMessageConverter) cannot bind
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ExceptionResponse> handleMethodArgumentNotValidException(
        MethodArgumentNotValidException e) {
        log.error("MethodArgumentNotValidException", e);
        final ExceptionResponse response = new ExceptionResponse(
            CommonExceptionType.INTERNAL_SERVER_ERROR, e);

        return new ResponseEntity<>(response, BAD_REQUEST);
    }

    // When @RequestParam cannot bind an enum type
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<ExceptionResponse> handleMethodArgumentTypeMismatchException(
        MethodArgumentTypeMismatchException e) {
        log.error("MethodArgumentTypeMismatchException", e);
        final ExceptionResponse response = new ExceptionResponse(
            CommonExceptionType.INTERNAL_SERVER_ERROR, e);

        return new ResponseEntity<>(response, BAD_REQUEST);
    }

    // There is no handler
    @ExceptionHandler(NoHandlerFoundException.class)
    protected ResponseEntity<ExceptionResponse> handleNoHandlerFoundException(
        NoHandlerFoundException e) {
        log.error("NoHandlerFoundException", e);
        final ExceptionResponse response = new ExceptionResponse(
            CommonExceptionType.INTERNAL_SERVER_ERROR, e);

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    // There is no http method
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ResponseEntity<ExceptionResponse> handleHttpRequestMethodNotSupportedException(
        HttpRequestMethodNotSupportedException e) {
        log.error("HttpRequestMethodNotSupportedException", e);
        final ExceptionResponse response = new ExceptionResponse(
            CommonExceptionType.INTERNAL_SERVER_ERROR, e);

        return new ResponseEntity<>(response, HttpStatus.METHOD_NOT_ALLOWED);
    }

    // Authentication object doesn't have any authority
    @ExceptionHandler(AccessDeniedException.class)
    protected ResponseEntity<ExceptionResponse> handleAccessDeniedException(
        AccessDeniedException e) {
        log.error("AccessDeniedException", e);
        final ExceptionResponse response = new ExceptionResponse(
            CommonExceptionType.INTERNAL_SERVER_ERROR, e);

        return new ResponseEntity<>(response, BAD_REQUEST);
    }

}
