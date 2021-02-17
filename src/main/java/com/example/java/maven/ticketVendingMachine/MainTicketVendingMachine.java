package com.example.java.maven.ticketVendingMachine;

import java.util.HashMap;

public class MainTicketVendingMachine {

    public static void main(String[] args) {
        MessagePrinter messagePrinter = new MessagePrinter();
        UserInputProvider userInputProvider = new UserInputProvider();
        TicketPrinter ticketPrinter = new TicketPrinter(100, messagePrinter);
        TicketStorage ticketStorage = new TicketStorage();

        CoinStorage tempCoinStorage = new CoinStorage(new HashMap<>(), messagePrinter);
        CoinStorage mainCoinStorage = new CoinStorage(new HashMap<>(), messagePrinter);
        CoinStorage oddMoneyStorage = new CoinStorage(new HashMap<>(), messagePrinter);


        TicketVendingMachine ticketVendingMachine = new TicketVendingMachine(
                ticketStorage,
                mainCoinStorage,
                tempCoinStorage,
                oddMoneyStorage,
                messagePrinter,
                userInputProvider);


        mainCoinStorage.fillCoinStorageWithGivenNumberOfCoinsForDenomination(10);
        ticketVendingMachine.getTicketsFromUser();
        ticketStorage.getValueOfTicketsInStorage();
        ticketVendingMachine.getPaymentFromUser();
        while (!ticketVendingMachine.isOddMoneyGivenBackToUser()) {
            ticketVendingMachine.getPaymentFromUser();
        }
        tempCoinStorage.moveAllCoinsTo(mainCoinStorage);
        ticketPrinter.printTicketsFromTicketStorage(ticketStorage);
    }
}
