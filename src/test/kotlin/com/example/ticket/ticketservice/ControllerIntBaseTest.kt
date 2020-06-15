package com.example.ticket.ticketservice

import com.example.ticket.ticketservice.dto.AirportDTO
import com.example.ticket.ticketservice.dto.FlightDTO
import com.example.ticket.ticketservice.dto.PassengerDTO
import com.fasterxml.jackson.databind.ObjectMapper
import org.hamcrest.Matchers
import org.junit.jupiter.api.BeforeEach
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

open abstract class ControllerIntBaseTest(
        private val wac: WebApplicationContext,
        private val objectMapper: ObjectMapper
) : IntegrationBaseTest() {

    val datetimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
    val dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val timeFormat = DateTimeFormatter.ofPattern("HH:mm")

    lateinit var mockMvc: MockMvc

    @BeforeEach
    @Throws(Exception::class)
    fun setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build()
    }

    @Throws(java.lang.Exception::class)
    fun createAirport(iataCode: String, name: String, cityName: String): AirportDTO {
        val airportDTO = AirportDTO(iataCode, name, cityName)

        val content = objectMapper.writeValueAsString(airportDTO)
        val result = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/airports")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk).andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.iataCode", Matchers.`is`(airportDTO.iataCode)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.`is`(airportDTO.name)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cityName", Matchers.`is`(airportDTO.cityName)))
                .andDo(MockMvcResultHandlers.print()).andReturn()
        val resultContent = result.response.contentAsString
        return objectMapper.readValue(resultContent, AirportDTO::class.java)
    }

    @Throws(java.lang.Exception::class)
    fun createFlight(departureDate: LocalDateTime, arrivalDate: LocalDateTime, iataCodeFrom: String, iataCodeTo: String): FlightDTO {
        val flightDTO = FlightDTO(departureDate, arrivalDate, iataCodeFrom, iataCodeTo)

        val content = objectMapper.writeValueAsString(flightDTO)
        val result = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/flights")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk).andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.arrivalDate", Matchers.`is`(flightDTO.arrivalDate.format(datetimeFormat))))
                .andExpect(MockMvcResultMatchers.jsonPath("$.departureDate", Matchers.`is`(flightDTO.departureDate.format(datetimeFormat))))
                .andExpect(MockMvcResultMatchers.jsonPath("$.iataCodeFrom", Matchers.`is`(flightDTO.iataCodeFrom)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.iataCodeTO", Matchers.`is`(flightDTO.iataCodeTO)))
                .andDo(MockMvcResultHandlers.print()).andReturn()
        val resultContent = result.response.contentAsString
        return objectMapper.readValue(resultContent, FlightDTO::class.java)
    }

    @Throws(java.lang.Exception::class)
    fun createPassenger(name: String, dateofBirth: LocalDate): PassengerDTO {
        val passengerDTO = PassengerDTO(name, dateofBirth)

        val content = objectMapper.writeValueAsString(passengerDTO)
        val result = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/passengers")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk).andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.`is`(passengerDTO.name)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dateOfBirth", Matchers.`is`(passengerDTO.dateOfBirth.toString())))
                .andDo(MockMvcResultHandlers.print()).andReturn()
        val resultContent = result.response.contentAsString
        return objectMapper.readValue(resultContent, passengerDTO::class.java)
    }
}