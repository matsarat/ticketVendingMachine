package com.example.java.maven.ticketVendingMachine;

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
}
