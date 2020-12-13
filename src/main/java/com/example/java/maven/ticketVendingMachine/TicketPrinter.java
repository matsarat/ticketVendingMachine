package com.example.java.maven.ticketVendingMachine;

public class TicketPrinter {
    private int paperContainerState;
    private final static String PRINT_TICKETS = "Here are your tickets:";
    private final MessagePrinter messagePrinter;

    public TicketPrinter(int paperContainerState, MessagePrinter messagePrinter) {
        this.paperContainerState = paperContainerState;
        this.messagePrinter = messagePrinter;
    }

    public int getPaperContainerState() {
        return paperContainerState;
    }

    public boolean isPaperInPrinter() {
        if (paperContainerState > 0) {
            return true;
        }
        else {
            return false;
        }
    }

    public void printTicketsFromTicketStorage(TicketStorage ticketStorage) {
        messagePrinter.printMessage(PRINT_TICKETS);
        for (Ticket ticket : ticketStorage.getTicketStorage()) {
            messagePrinter.printMessage(ticket.toString());
            paperContainerState -= 1;
        }
    }
}
