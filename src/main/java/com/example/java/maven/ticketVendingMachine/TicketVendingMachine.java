package com.example.java.maven.ticketVendingMachine;


import java.util.ArrayList;
import java.util.List;

public class TicketVendingMachine {
    private final TicketPrinter ticketPrinter;
    private final TicketStorage ticketStorage;
    private final CoinStorage coinStorage;
    private final MessagePrinter messagePrinter;
    private final UserInputProvider userInputProvider;

    public TicketVendingMachine(TicketPrinter ticketPrinter,
                                TicketStorage ticketStorage,
                                CoinStorage coinStorage,
                                MessagePrinter messagePrinter,
                                UserInputProvider userInputProvider) {

        this.ticketPrinter = ticketPrinter;
        this.ticketStorage = ticketStorage;
        this.coinStorage = coinStorage;
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

    public Ticket getTicket() {
        String userInput = userInputProvider.getTypeOfTicket();
        try {
            return Ticket.matchUserInput(userInput.toUpperCase());
        } catch (IllegalArgumentException exception) {
            messagePrinter.printError(exception.getMessage());
            return getTicket();
        }
    }

    public void addCoinToTempCoinStorage() {
        coinStorage.getTempCoinStorage().put(getCoin(), getCoin().getValue());
    }

    public void moveCoinsFromTempToMainCoinStorage() {
        coinStorage.getMainCoinStorage().putAll(coinStorage.getTempCoinStorage());
    }

    public void giveBackOddMoney() {
        List<Coin> oddCoinsToGiveBack = new ArrayList<>();
        int ticketsPrice = ticketStorage.getValueOfTicketsInStorage();
        int valueOfCoinsInTempStorage = CoinStorage.getValueOfCoinsInStorage(coinStorage.getTempCoinStorage());
        int oddMoney = valueOfCoinsInTempStorage - ticketsPrice;


    }

    public void getPayment() {
        while (ticketStorage.getValueOfTicketsInStorage() > CoinStorage.getValueOfCoinsInStorage(coinStorage.getTempCoinStorage())) {
            addCoinToTempCoinStorage();
        }
    }
}
