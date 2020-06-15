package com.example.ticket.ticketservice.exception.handler

import com.fasterxml.jackson.annotation.JsonFormat
import org.springframework.http.HttpStatus
import java.util.*

data class ApiError(
        val status: HttpStatus? = null,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
        val date: Date? = null,

        val message: String? = null
)