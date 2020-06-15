package com.example.ticket.ticketservice.dto

import io.swagger.v3.oas.annotations.media.Schema
import javax.validation.constraints.NotBlank

data class AirportDTO(
        val id: Long?,

        @Schema(description = "IATA code", example = "EZE", required = true)
        @NotBlank(message = "IATA code is mandatory")
        val iataCode: String,

        @Schema(description = "airport name", example = "Ministro Pistarin", required = true)
        @NotBlank(message = "Airport name is mandatory")
        val name: String,

        @Schema(description = "airport city", example = "Buenos Aires", required = true)
        @NotBlank(message = "Airport city name is mandatory")
        val cityName: String) {
    constructor(iataCode: String, name: String, cityName: String) : this(null, iataCode, name, cityName)
}