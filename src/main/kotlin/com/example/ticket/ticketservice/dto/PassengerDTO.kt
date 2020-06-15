package com.example.ticket.ticketservice.dto

import com.fasterxml.jackson.annotation.JsonFormat
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDate
import javax.validation.constraints.NotNull

data class PassengerDTO(
        val id: Long? = null,

        @Schema(description = "Passenger name", example = "passenger1", required = true)
        @NotNull(message = "Passenger name is mandatory")
        val name: String,

        @Schema(description = "Date of Birth", example = "1980-01-01", required = true)
        @NotNull(message = "Date of Birth is mandatory")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        val dateOfBirth: LocalDate
) {
    constructor(name: String, dateOfBirth: LocalDate) : this(null, name, dateOfBirth)
}