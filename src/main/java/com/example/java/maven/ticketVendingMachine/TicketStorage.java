package com.example.java.maven.ticketVendingMachine;

import java.util.List;

public class TicketStorage {
    private final List<Ticket> ticketStorage;

    public TicketStorage(List<Ticket> ticketStorage) {
        this.ticketStorage = ticketStorage;
    }

    public void addTicketToStorage(Ticket ticket) {
        ticketStorage.add(ticket);
    }


}
