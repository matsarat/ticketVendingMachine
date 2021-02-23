package com.example.java.maven.ticketVendingMachine;

import java.util.HashMap;

public class MainTicketVendingMachine {

    public static void main(String[] args) {
        MessagePrinter messagePrinter = new MessagePrinter();
        UserInputProvider userInputProvider = new UserInputProvider();
        TicketPrinter ticketPrinter = new TicketPrinter(messagePrinter);
        TicketStorage ticketStorage = new TicketStorage();

        CoinStorage tempCoinStorage = new CoinStorage(new HashMap<>());
        CoinStorage mainCoinStorage = new CoinStorage(new HashMap<>());
        CoinStorage oddMoneyStorage = new CoinStorage(new HashMap<>());


        TicketVendingMachine ticketVendingMachine = new TicketVendingMachine(
                ticketStorage,
                mainCoinStorage,
                tempCoinStorage,
                oddMoneyStorage,
                messagePrinter,
                userInputProvider);


        mainCoinStorage.fillCoinStorageWithGivenNumberOfCoinsForDenomination(10);
        ticketVendingMachine.getTicketsFromUser();
        ticketVendingMachine.getPaymentFromUser();
        while (!ticketVendingMachine.isOddMoneyGivenBackToUser()) {
            ticketVendingMachine.getPaymentFromUser();
        }
        tempCoinStorage.moveAllCoinsTo(mainCoinStorage);
        ticketPrinter.printTicketsFromTicketStorage(ticketStorage);
    }
}
