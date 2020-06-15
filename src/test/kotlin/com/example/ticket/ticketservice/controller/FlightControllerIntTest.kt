package com.example.ticket.ticketservice.controller

import com.example.ticket.ticketservice.ControllerIntBaseTest
import com.example.ticket.ticketservice.dto.FlightDTO
import com.fasterxml.jackson.databind.ObjectMapper
import org.hamcrest.Matchers
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.web.context.WebApplicationContext
import java.time.LocalDateTime

@WebAppConfiguration
class FlightControllerIntTest(
        @Autowired
        private val wac: WebApplicationContext,

        @Autowired
        private val objectMapper: ObjectMapper
) : ControllerIntBaseTest(wac, objectMapper) {

    @Test
    @Throws(java.lang.Exception::class)
    fun create_flight_and_retrieve_it() {
        createAirport("EZE", "Ministro Pistarin", "Buenos Aires")
        createAirport("DUB", "Dublin", "Dublin")
        var flightDTO: FlightDTO = createFlight(LocalDateTime.of(2020, 1, 1, 23, 0, 0),
                LocalDateTime.of(2020, 1, 2, 11, 0, 0),
                "EZE",
                "DUB"
        )

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/flights/{id}", flightDTO.id)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk).andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.`is`(flightDTO.id?.toInt())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.arrivalDate", Matchers.`is`(flightDTO.arrivalDate.format(datetimeFormat))))
                .andExpect(MockMvcResultMatchers.jsonPath("$.departureDate", Matchers.`is`(flightDTO.departureDate.format(datetimeFormat))))
                .andExpect(MockMvcResultMatchers.jsonPath("$.iataCodeFrom", Matchers.`is`(flightDTO.iataCodeFrom)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.iataCodeTO", Matchers.`is`(flightDTO.iataCodeTO)))
                .andDo(MockMvcResultHandlers.print())
    }

    @Test
    @Throws(java.lang.Exception::class)
    fun given_flightId_tryToGetFlight_then_throw_flight_not_found() {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/flights/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isNotFound).andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status", Matchers.`is`(HttpStatus.NOT_FOUND.name)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message", Matchers.`is`("Flight not found with id=1")))
                .andDo(MockMvcResultHandlers.print())
    }

}