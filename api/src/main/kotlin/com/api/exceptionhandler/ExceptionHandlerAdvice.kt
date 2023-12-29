package com.api.exceptionhandler

import com.core.component.exception.CommonException
import com.core.component.exception.CommonExceptionType
import com.core.config.logger
import io.jsonwebtoken.ExpiredJwtException
import lombok.extern.slf4j.Slf4j
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException
import org.springframework.web.servlet.NoHandlerFoundException
import java.nio.file.AccessDeniedException

// TODO : For the admin, we need to add a controller advice with a corresponded exception.
@RestControllerAdvice
class ExceptionHandlerAdvice {
    val log = logger<ExceptionHandlerAdvice>()
    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception): ResponseEntity<ExceptionResponse> {
        log.error(e.message, e)
        val response = ExceptionResponse(
            CommonExceptionType.INTERNAL_SERVER_ERROR, e
        )
        return ResponseEntity.internalServerError().body<ExceptionResponse>(response)
    }

    @ExceptionHandler(RuntimeException::class)
    fun handleRuntimeException(e: RuntimeException): ResponseEntity<ExceptionResponse> {
        log.error(e.message, e)
        return ResponseEntity.internalServerError().body<ExceptionResponse>(
            ExceptionResponse(CommonExceptionType.INTERNAL_SERVER_ERROR, e)
        )
    }

    @ExceptionHandler(ExpiredJwtException::class)
    fun handleExpiredJwtException(e: ExpiredJwtException): ResponseEntity<ExceptionResponse> {
        log.error(e.message, e)
        return ResponseEntity<ExceptionResponse>(
            ExceptionResponse(CommonExceptionType.INTERNAL_SERVER_ERROR, e),
            HttpStatus.UNAUTHORIZED
        )
    }

//    @ExceptionHandler(InvalidDataAccessResourceUsageException::class)
//    fun handleRuntimeException(e: InvalidDataAccessResourceUsageException): ResponseEntity<ExceptionResponse> {
//        log.error(e.getMessage(), e)
//        val response: ExceptionResponse =
//            ExceptionResponse(CommonExceptionType.INTERNAL_SERVER_ERROR, e.getCause().getCause().getMessage())
//
//        return ResponseEntity<ExceptionResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR)
//    }

    @ExceptionHandler(CommonException::class)
    fun handleCustomInternalException(e: CommonException): ResponseEntity<*> {
        log.error(e.message, e)
        val responseEntity: ResponseEntity<*>
        when (e.type) {
            CommonExceptionType.INVALID_ACCESS_TOKEN -> responseEntity =
                ResponseEntity<ExceptionResponse>(ExceptionResponse(e), HttpStatus.UNAUTHORIZED)

            CommonExceptionType.NONEXIST_DATA_EXCEPTION_MSG -> responseEntity =
                ResponseEntity<ExceptionResponse>(ExceptionResponse(e), HttpStatus.BAD_REQUEST)

            CommonExceptionType.INVALID_REQUEST -> responseEntity = ResponseEntity.badRequest().body<CommonException>(e)
            else -> responseEntity = ResponseEntity.internalServerError().body<CommonException>(e)
        }
        return responseEntity
    }

    // When @RequestBody (HttpMessageConverter) cannot bind
    @ExceptionHandler(MethodArgumentNotValidException::class)
    protected fun handleMethodArgumentNotValidException(
        e: MethodArgumentNotValidException?
    ): ResponseEntity<ExceptionResponse> {
        log.error("MethodArgumentNotValidException", e)
        val response: ExceptionResponse = ExceptionResponse(
            CommonExceptionType.INTERNAL_SERVER_ERROR, e?.message.toString()
        )

        return ResponseEntity<ExceptionResponse>(response, HttpStatus.BAD_REQUEST)
    }

    // When @RequestParam cannot bind an enum type
    @ExceptionHandler(MethodArgumentTypeMismatchException::class)
    protected fun handleMethodArgumentTypeMismatchException(
        e: MethodArgumentTypeMismatchException?
    ): ResponseEntity<ExceptionResponse> {
        log.error("MethodArgumentTypeMismatchException", e)
        val response: ExceptionResponse = ExceptionResponse(
            CommonExceptionType.INTERNAL_SERVER_ERROR, e?.message.toString()
        )

        return ResponseEntity<ExceptionResponse>(response, HttpStatus.BAD_REQUEST)
    }

    // There is no handler
    @ExceptionHandler(NoHandlerFoundException::class)
    protected fun handleNoHandlerFoundException(
        e: NoHandlerFoundException?
    ): ResponseEntity<ExceptionResponse> {
        log.error("NoHandlerFoundException", e)
        val response: ExceptionResponse = ExceptionResponse(
            CommonExceptionType.INTERNAL_SERVER_ERROR, e?.message.toString()
        )

        return ResponseEntity<ExceptionResponse>(response, HttpStatus.NOT_FOUND)
    }

    // There is no http method
    @ExceptionHandler(HttpRequestMethodNotSupportedException::class)
    protected fun handleHttpRequestMethodNotSupportedException(
        e: HttpRequestMethodNotSupportedException?
    ): ResponseEntity<ExceptionResponse> {
        log.error("HttpRequestMethodNotSupportedException", e)
        val response = ExceptionResponse(
            CommonExceptionType.INTERNAL_SERVER_ERROR, e?.message.toString()
        )

        return ResponseEntity<ExceptionResponse>(response, HttpStatus.METHOD_NOT_ALLOWED)
    }

    // Authentication object doesn't have any authority
    @ExceptionHandler(AccessDeniedException::class)
    protected fun handleAccessDeniedException(
        e: AccessDeniedException
    ): ResponseEntity<ExceptionResponse> {
        log.error("AccessDeniedException", e)
        val response = ExceptionResponse(
            CommonExceptionType.INTERNAL_SERVER_ERROR, e
        )

        return ResponseEntity<ExceptionResponse>(response, HttpStatus.BAD_REQUEST)
    }
}
