package com.example.ticket.ticketservice.controller

import com.example.ticket.ticketservice.ControllerIntBaseTest
import com.example.ticket.ticketservice.dto.AirportDTO
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

@WebAppConfiguration
class AirportControllerIntTest(
        @Autowired
        private val wac: WebApplicationContext,

        @Autowired
        private val objectMapper: ObjectMapper
) : ControllerIntBaseTest(wac, objectMapper) {

    @Test
    @Throws(java.lang.Exception::class)
    fun create_airport_and_retrieve_it() {
        val airportDTO: AirportDTO = createAirport("EZE", "Ministro Pistarin", "Buenos Aires")

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/airports/code/{iataCode}", airportDTO.iataCode)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk).andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.`is`(airportDTO.id?.toInt())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.iataCode", Matchers.`is`(airportDTO.iataCode)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.`is`(airportDTO.name)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cityName", Matchers.`is`(airportDTO.cityName)))
                .andDo(MockMvcResultHandlers.print())
    }

    @Test
    @Throws(java.lang.Exception::class)
    fun given_airportCode_tryToGetAirport_then_throw_airport_not_found() {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/airports/code/{iataCode}", "EZE")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isNotFound).andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status", Matchers.`is`(HttpStatus.NOT_FOUND.name)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message", Matchers.`is`("Airport not found with code=EZE")))
                .andDo(MockMvcResultHandlers.print())
    }
}