package com.example.ticket.ticketservice.factory

import com.example.ticket.ticketservice.domain.Airport
import com.example.ticket.ticketservice.dto.AirportDTO
import org.springframework.stereotype.Component

@Component
class AirportDTOFactory {
    fun fromEntity(airport: Airport): AirportDTO {
        airport.getId()?.let {
            return AirportDTO(airport.getId()!!, airport.iataCode, airport.name, airport.cityName)
        }
        throw IllegalArgumentException("Unexisting id for airport=$airport")
    }
}