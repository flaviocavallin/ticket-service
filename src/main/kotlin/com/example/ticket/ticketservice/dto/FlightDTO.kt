package com.example.ticket.ticketservice.dto

import com.fasterxml.jackson.annotation.JsonFormat
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime
import javax.validation.constraints.NotBlank

data class FlightDTO(
        val id: Long?,

        @Schema(description = "Departure date and time", example = "2020-01-01 23:00", required = true)
        @NotBlank(message = "Departure date and time is mandatory")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
        val departureDate: LocalDateTime,

        @Schema(description = "Arrival date and time", example = "2020-01-01 23:00", required = true)
        @NotBlank(message = "Arrival date and time is mandatory")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
        val arrivalDate: LocalDateTime,

        @Schema(description = "IATA code origin", example = "EZE", required = true)
        @NotBlank(message = "IATA code origin is mandatory")
        val iataCodeFrom: String,

        @Schema(description = "IATA code destination", example = "EZE", required = true)
        @NotBlank(message = "IATA code destination is mandatory")
        val iataCodeTO: String
) {
    constructor(departureDate: LocalDateTime, arrivalDate: LocalDateTime, iataCodeFrom: String, iataCodeTO: String) :
            this(null, departureDate, arrivalDate, iataCodeFrom, iataCodeTO)
}