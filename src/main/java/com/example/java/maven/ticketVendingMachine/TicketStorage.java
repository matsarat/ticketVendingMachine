package com.example.java.maven.ticketVendingMachine;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class TicketStorage {
    private final Map<Ticket, Integer> tickets = new HashMap<>();

    public Map<Ticket, Integer> getTickets() {
        return new HashMap<>(tickets);
    }

    public void clear() {
        tickets.clear();
    }

    public void addTicket(Ticket ticket) {
        int currentNumberOfTickets = Optional.ofNullable(tickets.get(ticket)).orElse(0);
        tickets.put(ticket, currentNumberOfTickets + 1);
    }

    public int getValueOfTicketsInStorage() {
        int valueOfTicketsInStorage = 0;
        for (Ticket ticket : tickets.keySet()) {
            valueOfTicketsInStorage += (ticket.getValue() * tickets.get(ticket));
        }
        return valueOfTicketsInStorage;
    }
}
