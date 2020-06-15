package com.example.ticket.ticketservice.repository

import com.example.ticket.ticketservice.domain.Flight
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface FlightRepository : JpaRepository<Flight, Long>