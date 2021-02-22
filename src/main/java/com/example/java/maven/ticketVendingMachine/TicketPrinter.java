package com.example.java.maven.ticketVendingMachine;

import java.util.Map;

public class TicketPrinter {
    private final static String PRINT_TICKETS = "Here are your tickets:";
    private final MessagePrinter messagePrinter;

    public TicketPrinter(MessagePrinter messagePrinter) {
        this.messagePrinter = messagePrinter;
    }

    public void printTicketsFromTicketStorage(TicketStorage ticketStorage) {
        messagePrinter.printMessage(PRINT_TICKETS);
        for (Map.Entry<Ticket, Integer> ticketEntry : ticketStorage.getTickets().entrySet()) {
            int numberOfTicketsToPrint = ticketEntry.getValue();
            while (numberOfTicketsToPrint>0) {
                messagePrinter.printMessage(ticketEntry.getKey().toString());
                numberOfTicketsToPrint -= 1;
            }
        }
        ticketStorage.clear();
    }
}
