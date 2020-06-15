package com.example.ticket.ticketservice.dto

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

data class TicketDTO(
        val id: Long,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        val departureDate: LocalDate,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        val arrivalDate: LocalDate,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
        val departureTime: LocalTime,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
        val arrivalTime: LocalTime,

        val cityOfOrigin: String,
        val destinationCity: String,
        val passengerName: String,
        val passengerAge: Int,
        val hasLuggageStorage: Boolean,
        val price: Double
) {
    constructor(id: Long, departureDate: LocalDateTime, arrivalDate: LocalDateTime, cityOfOrigin: String,
                destinationCity: String, passengerName: String, passengerAge: Int, hasLuggageStorage: Boolean, price: Double) :
            this(id, departureDate.toLocalDate(), arrivalDate.toLocalDate(), departureDate.toLocalTime(), arrivalDate.toLocalTime(),
                    cityOfOrigin, destinationCity, passengerName, passengerAge, hasLuggageStorage, price)
}