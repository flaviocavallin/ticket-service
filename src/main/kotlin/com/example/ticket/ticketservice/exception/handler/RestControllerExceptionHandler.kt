package com.example.ticket.ticketservice.exception.handler

import com.example.ticket.ticketservice.exception.EntityNotFoundException
import org.slf4j.LoggerFactory
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.validation.ObjectError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.util.*
import java.util.function.Consumer
import java.util.stream.Collectors

/**
 * Custom exception handler
 */
@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
class RestControllerExceptionHandler : ResponseEntityExceptionHandler() {
    companion object {
        private val LOGGER = LoggerFactory.getLogger(RestControllerExceptionHandler::class.java)
    }

    @ExceptionHandler(EntityNotFoundException::class)
    fun notFound(ex: RuntimeException): ResponseEntity<ApiError?>? {
        val apiError = ApiError(HttpStatus.NOT_FOUND, Date(), ex.message)
        LOGGER.debug("Exception handled={}", apiError)
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError)
    }


    override fun handleMethodArgumentNotValid(ex: MethodArgumentNotValidException, headers: HttpHeaders, status: HttpStatus, request: WebRequest): ResponseEntity<Any> {
        val errors: MutableMap<String, String?> = HashMap()
        ex.bindingResult.allErrors.forEach(Consumer { error: ObjectError ->
            val fieldName = (error as FieldError).field
            val errorMessage = error.getDefaultMessage()
            errors[fieldName] = errorMessage
        })
        val mapAsString = errors.keys.stream().map { key: String -> key + "=" + errors[key] }
                .collect(Collectors.joining(", ", "{", "}"))
        val apiError = ApiError(HttpStatus.BAD_REQUEST, Date(), mapAsString)
        LOGGER.debug("Exception handleValidationExceptions={}", apiError)
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError)
    }
}

