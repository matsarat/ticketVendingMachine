package com.example.java.maven.ticketVendingMachine;

public class TicketPrinter {
    private final int paperContainerState;

    public TicketPrinter(int paperContainerState) {
        this.paperContainerState = paperContainerState;
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

}
