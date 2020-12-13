package com.example.java.maven.ticketVendingMachine;

import java.util.List;

public class TicketStorage {
    private final List<Ticket> ticketStorage;

    public TicketStorage(List<Ticket> ticketStorage) {
        this.ticketStorage = ticketStorage;
    }

    public List<Ticket> getTicketStorage() {
        return ticketStorage;
    }

    public void addTicketToStorage(Ticket ticket) {
        ticketStorage.add(ticket);
    }

    public int getValueOfTicketsInStorage() {
        int valueOfTicketsInStorage = 0;
        for (Ticket ticket : ticketStorage) {
            valueOfTicketsInStorage += ticket.getValue();
        }
        return valueOfTicketsInStorage;
    }
    
}
