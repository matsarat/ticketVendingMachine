package com.example.java.maven.ticketVendingMachine;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.junit.jupiter.api.BeforeEach;

import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;
import static org.assertj.core.api.Assertions.assertThat;


public class TicketPrinterTest {

    private final static String PRINT_TICKETS = "Here are your tickets:";
    MessagePrinter messagePrinter = Mockito.mock(MessagePrinter.class);
    TicketStorage ticketStorage = new TicketStorage();
    TicketPrinter ticketPrinter = new TicketPrinter(messagePrinter);

    @BeforeEach
    void putTicketsIntoTicketStorage() {
        for (Ticket ticket : Ticket.values()) {
            ticketStorage.addTicket(ticket);
        }
    }

    @Test
    void shouldPrintAllTicketsFromTicketStorage() {

//        when
        ticketPrinter.printTicketsFromTicketStorage(ticketStorage);


//        then
        then(messagePrinter)
                .should(times(1))
                .printMessage(PRINT_TICKETS);
        then(messagePrinter)
                .should(times(1))
                .printMessage("Ticket: N20 with value of: 400");
        then(messagePrinter)
                .should(times(1))
                .printMessage("Ticket: N1Ride with value of: 600");
        then(messagePrinter)
                .should(times(1))
                .printMessage("Ticket: HF20 with value of: 200");
        then(messagePrinter)
                .should(times(1))
                .printMessage("Ticket: HF1Ride with value of: 300");

        assertThat(ticketStorage.getTickets().size()).isEqualTo(0);
    }
}
