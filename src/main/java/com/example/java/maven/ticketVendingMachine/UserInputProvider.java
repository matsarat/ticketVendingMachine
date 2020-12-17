package com.example.java.maven.ticketVendingMachine;

import java.util.Scanner;

public class UserInputProvider {

    public String getString() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
