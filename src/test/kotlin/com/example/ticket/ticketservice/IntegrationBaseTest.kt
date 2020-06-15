package com.example.ticket.ticketservice

import org.junit.jupiter.api.BeforeEach
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.Rollback
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.transaction.annotation.Transactional

@Transactional
@Rollback
@ActiveProfiles("test")
@SpringBootTest(classes = [TicketServiceApplication::class])
open abstract class IntegrationBaseTest