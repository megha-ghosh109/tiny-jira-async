package com.example.tinyjira.repository;

import com.example.tinyjira.model.Ticket;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class TicketRepository {

    private final Map<String, Ticket> store = new ConcurrentHashMap<>();

    public void save(Ticket ticket) {
        store.put(ticket.getTicketId(), ticket);
    }

    public Optional<Ticket> findById(String id) {
        return Optional.ofNullable(store.get(id));
    }

    public void updateStatus(String id, String status) {
        Ticket ticket = store.get(id);
        if (ticket != null) {
            ticket.setStatus(status);
            ticket.setUpdatedAt(java.time.Instant.now());
        }
    }
}
