package com.example.ticket.ticketservice.domain

import java.time.LocalDate
import java.time.Period
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Entity
@Table(name = "passengers")
class Passenger(
        @NotBlank(message = "Passenger name is mandatory")
        @Column(name = "name", length = 50, unique = true, nullable = false)
        val name: String,

        @NotNull(message = "Date of birth is mandatory")
        @Column(name = "date_of_birth", nullable = false, columnDefinition = "DATE")
        val dateOfBirth: LocalDate
) : AbstractEntity<Long>() {
    fun getAge(): Int {
        return Period.between(dateOfBirth, LocalDate.now()).years
    }
}