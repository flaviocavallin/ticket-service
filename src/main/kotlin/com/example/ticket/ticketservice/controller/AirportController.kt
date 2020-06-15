package com.example.ticket.ticketservice.controller

import com.example.ticket.ticketservice.dto.AirportDTO
import com.example.ticket.ticketservice.service.AirportService
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
@RequestMapping(value = ["/api/v1/airports"])
@Tag(name = "airport", description = "Airport API")
@OpenAPIDefinition(info = Info(title = "Airport API", version = "1.0"), tags = [Tag(name = "airports")])
class AirportController(private val airportService: AirportService) {

    @Operation(summary = "Create a new airport", description = "", tags = ["airport"])
    @ApiResponses(value = [ApiResponse(responseCode = "201", description = "successful operation")])
    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun create(@Valid @RequestBody airportDTO: AirportDTO): AirportDTO {
        return airportService.create(airportDTO.iataCode, airportDTO.name, airportDTO.cityName)
    }

    @Operation(summary = "Get airport by the given code", description = "", tags = ["airport"])
    @ApiResponses(value = [ApiResponse(responseCode = "200", description = "successful operation"),
        ApiResponse(responseCode = "404", description = "Airport not found")])
    @GetMapping(value = ["/code/{iataCode}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getById(@PathVariable(value = "iataCode") iataCode: String): AirportDTO {
        return airportService.findByCode(iataCode)
    }
}