package com.example.java.maven.ticketVendingMachine;

public enum Coin {
    FIVEZL(500, "5 PLN"),
    TWOZL(200, "2 PLN" ),
    ONEZL(100, "1 PLN"),
    FIFTYGR(50, "0,50 PLN"),
    TWENTYGR(20, "0,20 PLN"),
    TENGR(10, "0,10 PLN");

    private final int value;
    private final String symbol;

    Coin(int value, String symbol) {
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
