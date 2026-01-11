package com.example.tinyjira;

import com.example.tinyjira.model.Ticket;
import com.example.tinyjira.service.TicketService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TicketFlowTest {

    @Autowired
    TicketService service;

    @Test
    void ticketIsCreatedAndPersistedAsync() throws Exception {
        String id = service.createTicket(
                "user1",
                "Test subject",
                "Test description",
                LocalDate.now().plusDays(1),
                "open"
        );

        Thread.sleep(300);

        Ticket ticket = service.getTicket(id).orElseThrow();
        assertEquals("open", ticket.getStatus());
        assertEquals("user1", ticket.getCreatedBy());
    }
}
