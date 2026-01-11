package com.example.tinyjira.service;

import com.example.tinyjira.event.EventWorker;
import com.example.tinyjira.event.TicketCreatedEvent;
import com.example.tinyjira.event.TicketUpdatedEvent;
import com.example.tinyjira.model.Ticket;
import com.example.tinyjira.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Service
public class TicketService {

    private final EventWorker worker;
    private final TicketRepository repository;

    public TicketService(EventWorker worker, TicketRepository repository) {
        this.worker = worker;
        this.repository = repository;
    }

    public String createTicket(String userId, String subject, String description,
                               LocalDate deadline, String status) {

        String id = UUID.randomUUID().toString();
        Instant now = Instant.now();

        Ticket ticket = new Ticket();
        ticket.setTicketId(id);
        ticket.setSubject(subject);
        ticket.setDescription(description);
        ticket.setDeadline(deadline);
        ticket.setStatus(status);
        ticket.setCreatedBy(userId);
        ticket.setCreatedAt(now);
        ticket.setUpdatedAt(now);

        worker.publish(new TicketCreatedEvent(ticket));
        return id;
    }

    public Optional<Ticket> getTicket(String id) {
        return repository.findById(id);
    }

    public void updateStatus(String id, String status) {
        worker.publish(new TicketUpdatedEvent(id, status));
    }
}
