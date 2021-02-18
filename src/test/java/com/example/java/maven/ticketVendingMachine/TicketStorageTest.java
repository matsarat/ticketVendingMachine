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
}
