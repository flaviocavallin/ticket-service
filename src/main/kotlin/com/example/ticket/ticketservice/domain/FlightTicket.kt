package com.example.ticket.ticketservice.domain

import javax.persistence.*

@Entity
@Table(name = "tickets")
class FlightTicket(
        @ManyToOne(optional = false, fetch = FetchType.LAZY)
        @JoinColumn(name = "flight_id", nullable = false)
        val flight: Flight,

        @ManyToOne(optional = false, fetch = FetchType.LAZY)
        @JoinColumn(name = "passenger_id", nullable = false)
        val passenger: Passenger,

        @Column(name = "price", nullable = false)
        val price: Double,

        @Column(name = "is_luggage_storaged", nullable = false)
        val luggageInStorage: Boolean
) : AbstractEntity<Long>()