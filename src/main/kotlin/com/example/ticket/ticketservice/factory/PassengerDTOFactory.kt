package com.example.ticket.ticketservice.factory

import com.example.ticket.ticketservice.domain.Passenger
import com.example.ticket.ticketservice.dto.PassengerDTO
import org.springframework.stereotype.Component

@Component
class PassengerDTOFactory {
    fun fromEntity(passenger: Passenger): PassengerDTO {
        passenger.getId()?.let {
            return PassengerDTO(it, passenger.name, passenger.dateOfBirth)
        }
        throw IllegalArgumentException("Unexisting id for passenger=$passenger")
    }
}