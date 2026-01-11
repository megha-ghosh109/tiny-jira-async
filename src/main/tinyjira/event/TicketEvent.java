package com.example.tinyjira.event;

import com.example.tinyjira.repository.TicketRepository;

public interface TicketEvent {
    void process(TicketRepository repository);
}
