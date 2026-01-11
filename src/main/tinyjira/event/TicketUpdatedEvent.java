package com.example.tinyjira.event;

import com.example.tinyjira.repository.TicketRepository;

public class TicketUpdatedEvent implements TicketEvent {

    private final String ticketId;
    private final String status;

    public TicketUpdatedEvent(String ticketId, String status) {
        this.ticketId = ticketId;
        this.status = status;
    }

    @Override
    public void process(TicketRepository repository) {
        repository.updateStatus(ticketId, status);
    }
}
