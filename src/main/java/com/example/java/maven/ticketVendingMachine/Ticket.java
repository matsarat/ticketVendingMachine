package com.example.java.maven.ticketVendingMachine;

import java.util.ArrayList;
import java.util.List;

public enum Ticket {
    NORMAL_20(4.0f, "N20"),
    NORMAL_1_RIDE(6.0f, "N1Ride"),
    HALFFARE_20(2.0f, "HF20"),
    HALFFARE_1_RIDE(3.0f, "HF1Ride");

    private final float value;
    private final String symbol;

    Ticket(float value, String symbol) {
        this.value = value;
        this.symbol = symbol;
    }

    public float getValue() {
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
