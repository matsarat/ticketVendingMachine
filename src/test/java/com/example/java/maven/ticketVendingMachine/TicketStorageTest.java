package com.example.java.maven.ticketVendingMachine;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TicketStorageTest {
    TicketStorage ticketStorage = new TicketStorage();


    @Test
    void shouldAddTicket() {
        Ticket ticket = Ticket.NORMAL_20;
//        when
        ticketStorage.addTicket(ticket);

//        then
        assertThat(ticketStorage.getTickets().get(Ticket.NORMAL_20)).isEqualTo(1);
        assertThat(ticketStorage.getTickets().size()).isEqualTo(1);
        assertThat(ticketStorage.getValueOfTicketsInStorage()).isEqualTo(400); //400 is expressed in groszs int value of "NORMAL_20" ticket
    }

    @Test
    void shouldGetValueOfTicketsInTicketStorage() {
        Ticket ticket1 = Ticket.NORMAL_20;
        Ticket ticket2 = Ticket.NORMAL_1_RIDE;

//        when
        ticketStorage.addTicket(ticket1);
        ticketStorage.addTicket(ticket2);
        ticketStorage.getValueOfTicketsInStorage();

//        then
        assertThat(ticketStorage.getValueOfTicketsInStorage()).isEqualTo(1000);
        assertThat(ticketStorage.getTickets().size()).isEqualTo(2);
    }

    @Test
    void shouldShowValueOfTicketsInPLN() {
        Ticket ticket1 = Ticket.NORMAL_20;
        Ticket ticket2 = Ticket.NORMAL_1_RIDE;

//        when
        ticketStorage.addTicket(ticket1);
        ticketStorage.addTicket(ticket2);
        ticketStorage.getValueOfTicketsInStorage();

//        then
        assertThat(ticketStorage.showValueOfTicketsInPLN()).isEqualTo(10.0f);
    }
}
