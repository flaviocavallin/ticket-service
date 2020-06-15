package com.example.ticket.ticketservice.controller

import com.example.ticket.ticketservice.ControllerIntBaseTest
import com.example.ticket.ticketservice.dto.FlightDTO
import com.example.ticket.ticketservice.dto.FlightTicketRequestDTO
import com.example.ticket.ticketservice.dto.PassengerDTO
import com.example.ticket.ticketservice.dto.TicketDTO
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
import java.time.LocalDate
import java.time.LocalDateTime

@WebAppConfiguration
class FlightTicketControllerIntTest(
        @Autowired
        private val wac: WebApplicationContext,

        @Autowired
        private val objectMapper: ObjectMapper

) : ControllerIntBaseTest(wac, objectMapper) {

    @Test
    @Throws(java.lang.Exception::class)
    fun create_ticket_and_retrieve_it() {
        createAirport("EZE", "Ministro Pistarin", "Buenos Aires")
        createAirport("DUB", "Dublin", "Dublin")
        var flightDTO: FlightDTO = createFlight(LocalDateTime.of(2020, 1, 1, 23, 0, 0),
                LocalDateTime.of(2020, 1, 2, 11, 0, 0),
                "EZE",
                "DUB"
        )

        val passengerDTO: PassengerDTO = createPassenger("name1", LocalDate.of(1980, 1, 1))

        val price = 10.2
        val luggageInStorage = true
        val ticketDTO = createTicket(flightDTO.id!!, passengerDTO.id!!, price, luggageInStorage)

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/tickets/{id}", ticketDTO.id)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk).andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.`is`(ticketDTO.id?.toInt())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.departureDate", Matchers.`is`(ticketDTO.departureDate.format(dateFormat))))
                .andExpect(MockMvcResultMatchers.jsonPath("$.arrivalDate", Matchers.`is`(ticketDTO.arrivalDate.format(dateFormat))))
                .andExpect(MockMvcResultMatchers.jsonPath("$.departureTime", Matchers.`is`(ticketDTO.departureTime.format(timeFormat))))
                .andExpect(MockMvcResultMatchers.jsonPath("$.arrivalTime", Matchers.`is`(ticketDTO.arrivalTime.format(timeFormat))))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cityOfOrigin", Matchers.`is`(ticketDTO.cityOfOrigin)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.destinationCity", Matchers.`is`(ticketDTO.destinationCity)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.passengerName", Matchers.`is`(ticketDTO.passengerName)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.passengerAge", Matchers.`is`(ticketDTO.passengerAge)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.hasLuggageStorage", Matchers.`is`(ticketDTO.hasLuggageStorage)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price", Matchers.`is`(ticketDTO.price)))
                .andDo(MockMvcResultHandlers.print())
    }

    @Test
    @Throws(java.lang.Exception::class)
    fun given_ticketId_tryToGetTicket_then_throw_ticket_not_found() {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/tickets/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isNotFound).andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status", Matchers.`is`(HttpStatus.NOT_FOUND.name)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message", Matchers.`is`("Ticket not found with id=1")))
                .andDo(MockMvcResultHandlers.print())
    }

    @Throws(java.lang.Exception::class)
    fun createTicket(flightId: Long, passengerId: Long, price: Double, luggageInStorage: Boolean): TicketDTO {
        val flightTicketRequest = FlightTicketRequestDTO(flightId, passengerId, price, luggageInStorage)

        val content = objectMapper.writeValueAsString(flightTicketRequest)
        val result = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/tickets")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk).andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(MockMvcResultHandlers.print()).andReturn()
        val resultContent = result.response.contentAsString
        return objectMapper.readValue(resultContent, TicketDTO::class.java)
    }
}