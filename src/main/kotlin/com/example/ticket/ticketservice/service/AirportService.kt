package com.example.ticket.ticketservice.service

import com.example.ticket.ticketservice.domain.Airport
import com.example.ticket.ticketservice.dto.AirportDTO
import com.example.ticket.ticketservice.exception.EntityNotFoundException
import com.example.ticket.ticketservice.factory.AirportDTOFactory
import com.example.ticket.ticketservice.repository.AirportRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AirportService(
        private val airportRepository: AirportRepository,
        private val airportDTOFactory: AirportDTOFactory
) {

    @Transactional(readOnly = false)
    fun create(iataCode: String, name: String, cityName: String): AirportDTO {
        val airport = airportRepository.save(Airport(iataCode, name, cityName))
        return airportDTOFactory.fromEntity(airport)
    }

    @Transactional(readOnly = true)
    fun findByCode(code: String): AirportDTO {
        val airport = airportRepository.findByIataCode(code).orElseThrow { EntityNotFoundException("Airport not found with code=$code") }
        return airportDTOFactory.fromEntity(airport)
    }

}