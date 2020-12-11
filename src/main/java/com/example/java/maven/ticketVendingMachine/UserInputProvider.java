package com.example.java.maven.ticketVendingMachine;

import java.util.Scanner;

public class UserInputProvider {

    MessagePrinter messagePrinter = new MessagePrinter();

    public String getTypeOfTicket() {
        messagePrinter.printMessage("" + '\n' +
                "What kind of ticket would you like to buy? ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public Integer getCoins() {
        messagePrinter.printMessage("" + '\n' +
                "Insert coins:? ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }


}
