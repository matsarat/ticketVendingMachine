package com.example.java.maven.ticketVendingMachine;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

import static org.mockito.BDDMockito.then;

import java.util.Arrays;
import java.util.HashMap;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;

public class TicketStorageTest {
    TicketStorage ticketStorage = new TicketStorage();


    @Test
    void shouldAddTicket() {
        Ticket ticket = Ticket.NORMAL_20;
//        when
        ticketStorage.addTicket(ticket);

//        then
        assertThat(ticketStorage.getTickets().size()).isEqualTo(1);
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
        assertThat(ticketStorage.showValueOfTicketsInPLN()).isEqualTo(10.0);
    }
}
