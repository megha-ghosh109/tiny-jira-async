package com.example.tinyjira.event;

import com.example.tinyjira.repository.TicketRepository;
import org.springframework.stereotype.Component;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Component
public class EventWorker {

    private final BlockingQueue<TicketEvent> queue = new LinkedBlockingQueue<>();
    private final TicketRepository repository;

    public EventWorker(TicketRepository repository) {
        this.repository = repository;
        new Thread(this::consume).start();
    }

    public void publish(TicketEvent event) {
        queue.offer(event);
    }

    private void consume() {
        while (true) {
            try {
                TicketEvent event = queue.take();
                event.process(repository);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
