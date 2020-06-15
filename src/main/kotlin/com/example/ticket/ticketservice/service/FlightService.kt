package com.example.ticket.ticketservice.service

import com.example.ticket.ticketservice.domain.Flight
import com.example.ticket.ticketservice.dto.FlightDTO
import com.example.ticket.ticketservice.exception.EntityNotFoundException
import com.example.ticket.ticketservice.factory.FlightDTOFactory
import com.example.ticket.ticketservice.repository.AirportRepository
import com.example.ticket.ticketservice.repository.FlightRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class FlightService(
        private val flightRepository: FlightRepository,
        private val airportRepository: AirportRepository,
        private val flightDTOFactory: FlightDTOFactory
) {

    @Transactional(readOnly = false)
    fun create(flightDTORequest: FlightDTO): FlightDTO {
        val airportOrigin = airportRepository.findByIataCode(flightDTORequest.iataCodeFrom).orElseThrow { EntityNotFoundException("Airport not found with code=${flightDTORequest.iataCodeFrom}") }
        val airportDestination = airportRepository.findByIataCode(flightDTORequest.iataCodeTO).orElseThrow { EntityNotFoundException("Airport not found with code=${flightDTORequest.iataCodeTO}") }

        val flight = flightRepository.save(Flight(flightDTORequest.departureDate, flightDTORequest.arrivalDate, airportOrigin, airportDestination))
        return flightDTOFactory.fromEntity(flight)
    }

    @Transactional(readOnly = true)
    fun findById(id: Long): FlightDTO {
        val flight = flightRepository.findById(id).orElseThrow { EntityNotFoundException("Flight not found with id=$id") }
        return flightDTOFactory.fromEntity(flight)
    }

}