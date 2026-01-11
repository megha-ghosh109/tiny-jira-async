package com.example.tinyjira.event;

import com.example.tinyjira.model.Ticket;
import com.example.tinyjira.repository.TicketRepository;

public class TicketCreatedEvent implements TicketEvent {

    private final Ticket ticket;

    public TicketCreatedEvent(Ticket ticket) {
        this.ticket = ticket;
    }

    @Override
    public void process(TicketRepository repository) {
        repository.save(ticket);
    }
}
