package com.example.java.maven.ticketVendingMachine;

public enum Coin {
    FIVEZL(5.0f, "5 zl"),
    TWOZL(2.0f, "2 zl" ),
    ONEZL(1.0f, "1 zl"),
    FIFTYGR(0.50f, "50 gr"),
    TWENTYGR(0.20f, "20 gr"),
    TENGR(0.10f, "10 gr");

    private final float value;
    private final String symbol;

    Coin(float value, String symbol) {
        this.value = value;
        this.symbol = symbol;
    }

    public float getValue() {
        return value;
    }

    public String getSymbol() {
        return symbol;
    }
}
