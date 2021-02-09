package com.example.java.maven.ticketVendingMachine;

import java.util.HashMap;
import java.util.Map;

public class MainTicketVendingMachine {

    public static void main(String[] args) {
        MessagePrinter messagePrinter = new MessagePrinter();
        UserInputProvider userInputProvider = new UserInputProvider();
        TicketPrinter ticketPrinter = new TicketPrinter(100, messagePrinter);
        TicketStorage ticketStorage = new TicketStorage();

        Map<Coin, Integer> mainCoins = new HashMap<>();
        Map<Coin, Integer> tempCoins = new HashMap<>();
        Map<Coin, Integer> oddCoins = new HashMap<>();


        CoinStorage tempCoinStorage = new CoinStorage(tempCoins, messagePrinter);
        CoinStorage mainCoinStorage = new CoinStorage(mainCoins, messagePrinter);
        CoinStorage oddMoneyStorage = new CoinStorage(oddCoins, messagePrinter);


        TicketVendingMachine ticketVendingMachine = new TicketVendingMachine(
                ticketPrinter,
                ticketStorage,
                mainCoinStorage,
                tempCoinStorage,
                oddMoneyStorage,
                messagePrinter,
                userInputProvider);

        ticketVendingMachine.fillMainCoinStorage();
        ticketVendingMachine.getTicketsFromUser();
        ticketStorage.getValueOfTicketsInStorage();
        ticketVendingMachine.getPaymentFromUser();
        ticketVendingMachine.giveBackOddMoney();
        CoinStorage.moveCoinsFromTempToMainCoinStorage(mainCoinStorage, tempCoinStorage);
        ticketPrinter.printTicketsFromTicketStorage(ticketStorage);

    }
}
