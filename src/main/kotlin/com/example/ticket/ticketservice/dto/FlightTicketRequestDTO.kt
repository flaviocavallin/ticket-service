package com.example.ticket.ticketservice.dto

import io.swagger.v3.oas.annotations.media.Schema
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class FlightTicketRequestDTO(

        @Schema(description = "Flight id", example = "1", required = true)
        @NotNull(message = "Flight id is mandatory")
        val flightId: Long,

        @Schema(description = "Passenger id", example = "1", required = true)
        @NotNull(message = "Passenger id is mandatory")
        val passengerId: Long,

        @Schema(description = "Price", example = "100.1", required = true)
        @NotNull(message = "Price id is mandatory")
        val price: Double,

        @Schema(description = "Luggage is In Storage", example = "true", required = true)
        @NotNull(message = "Luggage is In Storage is mandatory")
        val luggageInStorage: Boolean
)