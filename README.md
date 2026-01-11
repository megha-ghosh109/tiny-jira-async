# Tiny Jira – Async Ticket Pipeline

This project simulates an asynchronous ticket system using in-process events.

## Features
- Create Ticket (async persistence)
- Get Ticket
- Update Ticket Status (event-driven)
- Background worker thread
- In-memory storage
- Automated integration test

## Run
mvn spring-boot:run

## Test
mvn test

## Design Notes
- Events decouple API from persistence
- Worker simulates async pipeline
- Easy to extend to DB or message queues
