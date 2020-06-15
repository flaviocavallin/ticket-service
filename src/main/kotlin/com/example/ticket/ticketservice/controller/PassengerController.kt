package com.example.ticket.ticketservice.controller

import com.example.ticket.ticketservice.dto.PassengerDTO
import com.example.ticket.ticketservice.service.PassengerService
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
@RequestMapping(value = ["/api/v1/passengers"])
@Tag(name = "passenger", description = "Passenger API")
@OpenAPIDefinition(info = Info(title = "Passenger API", version = "1.0"), tags = [Tag(name = "passenger")])
class PassengerController(private val passengerService: PassengerService) {

    @Operation(summary = "Create a new Passenger", description = "", tags = ["passenger"])
    @ApiResponses(value = [ApiResponse(responseCode = "201", description = "successful operation")])
    @PostMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun createPassenger(@Valid @RequestBody passengerDTO: PassengerDTO): PassengerDTO {
        return passengerService.create(passengerDTO.name, passengerDTO.dateOfBirth)
    }

    @Operation(summary = "Get passenger by the given id", description = "", tags = ["passenger"])
    @ApiResponses(value = [ApiResponse(responseCode = "200", description = "successful operation"),
        ApiResponse(responseCode = "404", description = "Passenger not found")])
    @GetMapping(value = ["/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getById(@PathVariable(value = "id") id: Long): PassengerDTO {
        return passengerService.findById(id)
    }
}