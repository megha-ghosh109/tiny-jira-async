package com.example.tinyjira.controller;

import com.example.tinyjira.model.Ticket;
import com.example.tinyjira.service.TicketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    private final TicketService service;

    public TicketController(TicketService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<String> createTicket(@RequestBody Map<String, String> body) {
        String id = service.createTicket(
                body.get("userId"),
                body.get("subject"),
                body.get("description"),
                LocalDate.parse(body.get("deadline")),
                body.get("status")
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ticket> getTicket(@PathVariable String id) {
        return service.getTicket(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Void> updateStatus(
            @PathVariable String id,
            @RequestBody Map<String, String> body) {

        service.updateStatus(id, body.get("status"));
        return ResponseEntity.accepted().build();
    }
}
