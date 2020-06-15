package com.example.ticket.ticketservice.exception.handler

import org.slf4j.LoggerFactory
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

/**
 * Default exception handler for all exceptions that where missed to catch by an
 * specific handler
 */
@RestControllerAdvice
@Order(Ordered.LOWEST_PRECEDENCE)
class DefaultExceptionHandler : ResponseEntityExceptionHandler() {

    companion object {
        private val LOGGER = LoggerFactory.getLogger(DefaultExceptionHandler::class.java)
    }

    @ExceptionHandler(Exception::class, RuntimeException::class)
    fun handleDefaultException(ex: Exception?): ResponseEntity<Void?>? {
        LOGGER.error("There was an unexpected error", ex)
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
    }
}