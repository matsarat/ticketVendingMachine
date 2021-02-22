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


//        given
        ticketStorage.addTicket(ticket1);
        ticketStorage.addTicket(ticket2);

//        when
        int value = ticketStorage.getValueOfTicketsInStorage();

//        then
        assertThat(value).isEqualTo(1000); // 1000 is expected value, because when we take one ticket of a kind and sum up their values it will be 1000 groszs
        assertThat(ticketStorage.getTickets().size()).isEqualTo(2);
    }

    @Test
    void shouldShowValueOfTicketsInPLN() {
        Ticket ticket1 = Ticket.NORMAL_20;
        Ticket ticket2 = Ticket.NORMAL_1_RIDE;

//        given
        ticketStorage.addTicket(ticket1);
        ticketStorage.addTicket(ticket2);

//        then
        assertThat(ticketStorage.showValueOfTicketsInPLN()).isEqualTo(10.0f);
    }
}
