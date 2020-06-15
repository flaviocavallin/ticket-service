package com.example.ticket.ticketservice.repository

import com.example.ticket.ticketservice.domain.FlightTicket
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface FlightTicketRepository : JpaRepository<FlightTicket, Long>