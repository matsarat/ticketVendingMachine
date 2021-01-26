package com.example.java.maven.ticketVendingMachine;

import java.util.ArrayList;
import java.util.List;

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

    public int getCoinValue() {
        return value;
    }

    public String getCoinSymbol() {
        return symbol;
    }

    public static List<String> getAllCoinSymbols() {
        List<String> allCoinSymbols = new ArrayList<>();
        for (Coin coin : Coin.values()) {
            allCoinSymbols.add(coin.getCoinSymbol());
        }
        return allCoinSymbols;
    }

    public static List<Integer> getAllCoinValues() {
        List<Integer> allCoinValues = new ArrayList<>();
        for (Coin coin : Coin.values()) {
            allCoinValues.add(coin.getCoinValue());
        }
        return allCoinValues;
    }

    public static Coin matchUserInput(String userInput) {
        for (Coin coin : Coin.values()) {
            if (coin.getCoinSymbol().equalsIgnoreCase(userInput)) {
                return coin;
            }
        }
        throw new IllegalArgumentException(String.format("Invalid coin: %s. Valid coins: %s", userInput, getAllCoinSymbols()));
    }

    @Override
    public String toString() {
        return "Coin " + symbol;
    }
}