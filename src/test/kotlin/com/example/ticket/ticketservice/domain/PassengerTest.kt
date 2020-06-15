package com.example.ticket.ticketservice.domain

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import java.time.LocalDate

class PassengerTest {
    @Test
    fun should_return_passenger_age() {
        val year = 1980;
        val passenger = Passenger("name1", LocalDate.of(year, 1, 1))
        Assertions.assertThat(passenger.getAge()).isEqualTo(LocalDate.now().year - year)
    }
}
