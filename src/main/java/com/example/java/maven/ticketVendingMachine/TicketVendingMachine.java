package com.example.java.maven.ticketVendingMachine;


public class TicketVendingMachine {
    private final Ticket ticket;
    private final TicketPrinter ticketPrinter;
    private final TicketStorage ticketStorage;
    private final MessagePrinter messagePrinter;
    private final UserInputProvider userInputProvider;

    public TicketVendingMachine(Coin coin,
                                Ticket ticket,
                                TicketPrinter ticketPrinter,
                                TicketStorage ticketStorage,
                                MessagePrinter messagePrinter,
                                UserInputProvider userInputProvider) {

        this.ticket = ticket;
        this.ticketPrinter = ticketPrinter;
        this.ticketStorage = ticketStorage;
        this.messagePrinter = messagePrinter;
        this.userInputProvider = userInputProvider;

    }

    public Coin getCoin() {
        String userInput = userInputProvider.getCoins();
        try {
            return Coin.matchUserInput(userInput.toUpperCase());
        } catch (IllegalArgumentException exception) {
            messagePrinter.printError(exception.getMessage());
            return getCoin();
        }
    }


}
