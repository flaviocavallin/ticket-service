package com.example.ticket.ticketservice.controller

import com.example.ticket.ticketservice.dto.FlightTicketRequestDTO
import com.example.ticket.ticketservice.dto.TicketDTO
import com.example.ticket.ticketservice.service.TicketService
import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.info.Info
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping(value = ["/api/v1/tickets"])
@Tag(name = "ticket", description = "Ticket API")
@OpenAPIDefinition(info = Info(title = "Ticket API", version = "1.0"), tags = [Tag(name = "ticket")])
class FlightTicketController(private val ticketService: TicketService) {

    @Operation(summary = "Create a new Ticket", description = "", tags = ["ticket"])
    @ApiResponses(value = [ApiResponse(responseCode = "201", description = "successful operation")])
    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun create(@Valid @RequestBody ticketRequestDTO: FlightTicketRequestDTO): TicketDTO {
        return ticketService.create(ticketRequestDTO.flightId, ticketRequestDTO.passengerId, ticketRequestDTO.price, ticketRequestDTO.luggageInStorage)
    }

    @Operation(summary = "Get ticket by the given id", description = "", tags = ["ticket"])
    @ApiResponses(value = [ApiResponse(responseCode = "200", description = "successful operation"),
        ApiResponse(responseCode = "404", description = "Ticket not found")])
    @GetMapping(value = ["/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getById(@PathVariable(value = "id") id: Long): TicketDTO {
        return ticketService.findById(id)
    }
}