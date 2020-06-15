package com.example.ticket.ticketservice.repository

import com.example.ticket.ticketservice.domain.Passenger
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PassengerRepository : JpaRepository<Passenger, Long>