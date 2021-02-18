package com.example.java.maven.ticketVendingMachine;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TicketTest {

    @Test
    void shouldReturnListOfAllTicketSymbols() {

//        when
        Ticket.getAllTicketSymbols();

//        then
        assertThat(Ticket.getAllTicketSymbols().size()).isEqualTo(4);
    }

    @Test
    void shouldReturnTicket() {

//        given
        String userInput1 = "hf20";
        String userInput2 = "n20";
        String userInput3 = "hf1ride";
        String userInput4 = "n1ride";

//        when
        Ticket.matchUserInput(userInput1);
        Ticket.matchUserInput(userInput2);
        Ticket.matchUserInput(userInput3);
        Ticket.matchUserInput(userInput4);

//        then
        assertThat(Ticket.matchUserInput(userInput1))
                .isEqualTo(Ticket.HALFFARE_20);
        assertThat(Ticket.matchUserInput(userInput2))
                .isEqualTo(Ticket.NORMAL_20);
        assertThat(Ticket.matchUserInput(userInput3))
                .isEqualTo(Ticket.HALFFARE_1_RIDE);
        assertThat(Ticket.matchUserInput(userInput4))
                .isEqualTo(Ticket.NORMAL_1_RIDE);
    }


    @Test
    void shouldThrowIllegalArgumentException() {

//        given

        String userInput1 = "hf 20";
        String userInput2 = "hf  20";
        String userInput3 = "hf.20";
        String userInput4 = "hf-20";
        String userInput5 = "hf 22";

//        then
        assertThrows(IllegalArgumentException.class,
                () -> Ticket.matchUserInput(userInput1));
        assertThrows(IllegalArgumentException.class,
                () -> Ticket.matchUserInput(userInput2));
        assertThrows(IllegalArgumentException.class,
                () -> Ticket.matchUserInput(userInput3));
        assertThrows(IllegalArgumentException.class,
                () -> Ticket.matchUserInput(userInput4));
        assertThrows(IllegalArgumentException.class,
                () -> Ticket.matchUserInput(userInput5));
    }
}
