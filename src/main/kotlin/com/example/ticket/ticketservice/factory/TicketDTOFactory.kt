package com.example.ticket.ticketservice.factory

import com.example.ticket.ticketservice.domain.FlightTicket
import com.example.ticket.ticketservice.dto.TicketDTO
import org.springframework.stereotype.Component

@Component
class TicketDTOFactory {
    fun fromEntity(ticket: FlightTicket): TicketDTO {
        ticket.getId()?.let {
            return TicketDTO(it,
                    ticket.flight.departureDate,
                    ticket.flight.arrivalDate,
                    ticket.flight.originAirport.cityName,
                    ticket.flight.destinationAirport.cityName,
                    ticket.passenger.name,
                    ticket.passenger.getAge(),
                    ticket.luggageInStorage,
                    ticket.price
            )
        }
        throw IllegalArgumentException("Unexisting id for FlightTicket=$ticket")
    }
}