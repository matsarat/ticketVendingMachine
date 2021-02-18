package com.example.java.maven.ticketVendingMachine;

import java.util.ArrayList;
import java.util.List;

public enum Ticket {
    NORMAL_20(400, "N20"),
    NORMAL_1_RIDE(600, "N1Ride"),
    HALFFARE_20(200, "HF20"),
    HALFFARE_1_RIDE(300, "HF1Ride");

    private final int value;
    private final String symbol;

    Ticket(int value, String symbol) {
        this.value = value;
        this.symbol = symbol;
    }

    public int getValue() {
        return value;
    }

    public String getSymbol() {
        return symbol;
    }

    @Override
    public String toString() {
        return String.format("Ticket: %s with value of: %s", symbol, value);

    }

    public static List<String> getAllTicketSymbols() {
        List<String> allTicketSymbols = new ArrayList<>();
        for (Ticket ticket : Ticket.values()) {
            allTicketSymbols.add(ticket.getSymbol());
        }
        return allTicketSymbols;
    }

    public static Ticket matchUserInput(String userInput) {
        for (Ticket ticket : Ticket.values()) {
            if (ticket.getSymbol().equalsIgnoreCase(userInput)) {
                return ticket;
            }
        }
        throw new IllegalArgumentException(String.format("Invalid ticket: %s. Available tickets: %s", userInput, getAllTicketSymbols()));
    }


}
