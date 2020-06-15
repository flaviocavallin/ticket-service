package com.example.ticket.ticketservice.domain

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "flights")
class Flight(
        @Column(name = "departure_date", columnDefinition = "TIMESTAMP", nullable = false)
        val departureDate: LocalDateTime,

        @Column(name = "arrival_date", columnDefinition = "TIMESTAMP", nullable = false)
        val arrivalDate: LocalDateTime,

        @ManyToOne(optional = false, fetch = FetchType.LAZY)
        @JoinColumn(name = "origin_airport_id", nullable = false)
        val originAirport: Airport,

        @ManyToOne(optional = false, fetch = FetchType.LAZY)
        @JoinColumn(name = "destination_airport_id", nullable = false)
        val destinationAirport: Airport
) : AbstractEntity<Long>()