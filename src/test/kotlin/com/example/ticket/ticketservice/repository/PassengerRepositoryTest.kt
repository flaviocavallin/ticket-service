package com.example.ticket.ticketservice.repository

import com.example.ticket.ticketservice.IntegrationBaseTest
import com.example.ticket.ticketservice.domain.Passenger
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import java.time.LocalDate

class PassengerRepositoryTest(
        @Autowired
        private val passengerRepository: PassengerRepository
) : IntegrationBaseTest() {

    @Test
    fun create_passenger_and_retrieve_it_by_given_id() {
        val name = "passenger 1"
        val dateOfBirth = LocalDate.of(1980, 1, 1);
        val passenger = Passenger(name, dateOfBirth)
        passengerRepository.saveAndFlush(passenger)
        val p = passengerRepository.findById(passenger.getId()!!).get()

        Assertions.assertThat(passenger).isEqualToComparingFieldByField(p)
    }
}