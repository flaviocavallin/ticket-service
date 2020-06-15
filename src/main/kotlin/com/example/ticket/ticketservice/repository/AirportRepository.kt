package com.example.ticket.ticketservice.repository

import com.example.ticket.ticketservice.domain.Airport
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface AirportRepository : JpaRepository<Airport, Long> {
    fun findByIataCode(iataCode: String): Optional<Airport>
}