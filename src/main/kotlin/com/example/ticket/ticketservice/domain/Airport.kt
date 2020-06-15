package com.example.ticket.ticketservice.domain

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table
import javax.validation.constraints.NotBlank

@Entity
@Table(name = "airports")
class Airport(
        @NotBlank(message = "IATA code is mandatory")
        @Column(name = "iata_code", length = 5, unique = true, nullable = false)
        val iataCode: String,

        @NotBlank(message = "Airport name is mandatory")
        @Column(name = "name", length = 50, nullable = false)
        val name: String,

        @NotBlank(message = "City name is mandatory")
        @Column(name = "city_name", length = 50, nullable = false)
        val cityName: String

) : AbstractEntity<Long>()