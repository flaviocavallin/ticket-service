package com.example.ticket.ticketservice.exception.handler

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus

class DefaultExceptionHandlerTest {
    @Test
    fun defaultExceptionHandlerTest() {
        val defaultExceptionHandler = DefaultExceptionHandler()
        val responseEntity = defaultExceptionHandler
                .handleDefaultException(RuntimeException("Test default exception handler"))
        Assertions.assertThat(responseEntity!!.body).isNull()
        Assertions.assertThat(responseEntity.statusCode).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR)
    }
}