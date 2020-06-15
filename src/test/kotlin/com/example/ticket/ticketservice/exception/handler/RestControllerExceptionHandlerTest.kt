package com.example.ticket.ticketservice.exception.handler

import com.example.ticket.ticketservice.exception.EntityNotFoundException
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus

class RestControllerExceptionHandlerTest {
    companion object {
        private const val ERROR_MESSAGE = "Passenger not found for id=1"
    }

    @Test
    fun restControllerExceptionHandlerTest() {
        val handler = RestControllerExceptionHandler()
        val responseEntity = handler.notFound(EntityNotFoundException(ERROR_MESSAGE))
        Assertions.assertThat(responseEntity!!.body).isNotNull
        Assertions.assertThat(responseEntity.body?.status).isEqualTo(HttpStatus.NOT_FOUND)
        Assertions.assertThat(responseEntity.body?.message).isEqualTo(ERROR_MESSAGE)
        Assertions.assertThat(responseEntity.statusCode).isEqualTo(HttpStatus.NOT_FOUND)
    }
}