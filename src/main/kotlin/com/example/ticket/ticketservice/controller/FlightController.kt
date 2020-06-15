package com.example.ticket.ticketservice.controller

import com.example.ticket.ticketservice.dto.FlightDTO
import com.example.ticket.ticketservice.service.FlightService
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
@RequestMapping(value = ["/api/v1/flights"])
@Tag(name = "flight", description = "Flight API")
@OpenAPIDefinition(info = Info(title = "Flight API", version = "1.0"), tags = [Tag(name = "flight")])
class FlightController(private val flightService: FlightService) {

    @Operation(summary = "Create a new flight", description = "", tags = ["flight"])
    @ApiResponses(value = [ApiResponse(responseCode = "201", description = "successful operation")])
    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun create(@Valid @RequestBody flightDTO: FlightDTO): FlightDTO {
        return flightService.create(flightDTO)
    }

    @Operation(summary = "Get flight by the given id", description = "", tags = ["flight"])
    @ApiResponses(value = [ApiResponse(responseCode = "200", description = "successful operation"),
        ApiResponse(responseCode = "404", description = "Flight not found")])
    @GetMapping(value = ["/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getById(@PathVariable(value = "id") id: Long): FlightDTO {
        return flightService.findById(id)
    }
}