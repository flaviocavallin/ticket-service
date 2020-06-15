package com.example.ticket.ticketservice.controller

import com.example.ticket.ticketservice.ControllerIntBaseTest
import com.example.ticket.ticketservice.IntegrationBaseTest
import com.example.ticket.ticketservice.dto.PassengerDTO
import com.fasterxml.jackson.databind.ObjectMapper
import org.hamcrest.Matchers
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import java.time.LocalDate

@WebAppConfiguration
class PassengerControllerIntTest(
        @Autowired
        private val wac: WebApplicationContext,

        @Autowired
        private val objectMapper: ObjectMapper

) : ControllerIntBaseTest(wac, objectMapper) {

    @Test
    @Throws(java.lang.Exception::class)
    fun create_passenger_and_retrieve_it() {

        val passengerDTO: PassengerDTO = createPassenger("name1", LocalDate.of(1980, 1, 1))

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/passengers/{id}", passengerDTO.id)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk).andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.`is`(passengerDTO.id?.toInt())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.`is`(passengerDTO.name)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dateOfBirth", Matchers.`is`(passengerDTO.dateOfBirth.toString())))
                .andDo(MockMvcResultHandlers.print())
    }

    @Test
    @Throws(java.lang.Exception::class)
    fun given_passengerId_tryToGetPassenger_then_throw_passenger_not_found() {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/passengers/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isNotFound).andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status", Matchers.`is`(HttpStatus.NOT_FOUND.name)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message", Matchers.`is`("Passenger not found with id=1")))
                .andDo(MockMvcResultHandlers.print())
    }
}