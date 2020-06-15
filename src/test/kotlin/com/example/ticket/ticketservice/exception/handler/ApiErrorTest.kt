package com.example.ticket.ticketservice.exception.handler

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus
import java.util.*

class ApiErrorTest {
    @Test
    fun apiErrorTest() {
        val status = HttpStatus.BAD_REQUEST
        val date = Date()
        val message = "message"
        val apiError = ApiError(status, date, message)
        Assertions.assertThat(apiError.status).isEqualTo(status)
        Assertions.assertThat(apiError.date).isEqualTo(date)
        Assertions.assertThat(apiError.message).isEqualTo(message)
    }
}