package com.example.java.maven.ticketVendingMachine;

import java.util.Map;

public class TicketPrinter {
    private int paperContainerState;
    private final static String PRINT_TICKETS = "Here are your tickets:";
    private final MessagePrinter messagePrinter;

    public TicketPrinter(int paperContainerState, MessagePrinter messagePrinter) {
        this.paperContainerState = paperContainerState;
        this.messagePrinter = messagePrinter;
    }

    public void printTicketsFromTicketStorage(TicketStorage ticketStorage) {
        messagePrinter.printMessage(PRINT_TICKETS);
        for (Map.Entry<Ticket, Integer> ticketEntry : ticketStorage.getTickets().entrySet()) {
            int numberOfTicketsToPrint = ticketEntry.getValue();
            while (numberOfTicketsToPrint>0) {
                messagePrinter.printMessage(ticketEntry.getKey().toString());
                paperContainerState -= 1;
                numberOfTicketsToPrint -= 1;
            }
        }
        ticketStorage.clear();
    }
}
