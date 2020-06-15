package com.example.ticket.ticketservice.factory

import com.example.ticket.ticketservice.domain.Flight
import com.example.ticket.ticketservice.dto.FlightDTO
import org.springframework.stereotype.Component

@Component
class FlightDTOFactory {
    fun fromEntity(flight: Flight): FlightDTO {
        flight.getId()?.let {
            return FlightDTO(it, flight.departureDate, flight.arrivalDate, flight.originAirport.iataCode, flight.destinationAirport.iataCode)
        }
        throw IllegalArgumentException("Unexisting id for flight=$flight")
    }
}