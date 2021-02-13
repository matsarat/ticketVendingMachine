package com.example.java.maven.ticketVendingMachine;

import java.util.Scanner;

public class UserInputProvider {
    MessagePrinter messagePrinter = new MessagePrinter();

    public String getString() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public Coin getCoin() {
        String userInput = getString();
        try {
            return Coin.matchUserInput(userInput.toUpperCase());
        } catch (IllegalArgumentException exception) {
            messagePrinter.printError(exception.getMessage());
            return getCoin();
        }
    }

    public Ticket getTicket() {
        String chosenTicket = getString();
        try {
            return Ticket.matchUserInput(chosenTicket.toUpperCase());
        } catch (IllegalArgumentException exception) {
            messagePrinter.printError(exception.getMessage());
            return getTicket();
        }
    }
}
