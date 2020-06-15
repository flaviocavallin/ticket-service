package com.example.ticket.ticketservice.service

import com.example.ticket.ticketservice.domain.FlightTicket
import com.example.ticket.ticketservice.dto.TicketDTO
import com.example.ticket.ticketservice.exception.EntityNotFoundException
import com.example.ticket.ticketservice.factory.TicketDTOFactory
import com.example.ticket.ticketservice.repository.FlightRepository
import com.example.ticket.ticketservice.repository.FlightTicketRepository
import com.example.ticket.ticketservice.repository.PassengerRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class TicketService(
        private val ticketRepository: FlightTicketRepository,
        private val flightRepository: FlightRepository,
        private val passengerRepository: PassengerRepository,
        private val ticketDTOFactory: TicketDTOFactory
) {

    @Transactional(readOnly = false)
    fun create(flightId: Long, passengerId: Long, price: Double, luggageInStorage: Boolean): TicketDTO {
        val flight = flightRepository.findById(flightId).orElseThrow { EntityNotFoundException("Flight not found with id=$flightId") }
        val passenger = passengerRepository.findById(passengerId).orElseThrow { EntityNotFoundException("Passenger not found with id=$passengerId") }

        val ticket = ticketRepository.save(FlightTicket(flight, passenger, price, luggageInStorage))

        return ticketDTOFactory.fromEntity(ticket)
    }

    @Transactional(readOnly = true)
    fun findById(id: Long): TicketDTO {
        val ticket = ticketRepository.findById(id).orElseThrow { EntityNotFoundException("Ticket not found with id=$id") }
        return ticketDTOFactory.fromEntity(ticket)
    }

}