package com.example.ticket.ticketservice.service

import com.example.ticket.ticketservice.domain.Passenger
import com.example.ticket.ticketservice.dto.PassengerDTO
import com.example.ticket.ticketservice.exception.EntityNotFoundException
import com.example.ticket.ticketservice.factory.PassengerDTOFactory
import com.example.ticket.ticketservice.repository.PassengerRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

@Service
class PassengerService(
        private val passengerRepository: PassengerRepository,
        private val passengerDTOFactory: PassengerDTOFactory
) {

    @Transactional(readOnly = false)
    fun create(name: String, dateOfBirth: LocalDate): PassengerDTO {
        val passenger = passengerRepository.save(Passenger(name, dateOfBirth))
        return passengerDTOFactory.fromEntity(passenger)
    }

    @Transactional(readOnly = true)
    fun findById(id: Long): PassengerDTO {
        val airport = passengerRepository.findById(id).orElseThrow { EntityNotFoundException("Passenger not found with id=$id") }
        return passengerDTOFactory.fromEntity(airport)
    }

}