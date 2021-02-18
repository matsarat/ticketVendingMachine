package com.example.java.maven.ticketVendingMachine;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CoinTest {


    @Test
    void shouldReturnListOfAllCoinSymbols() {

//        when
        Coin.getAllCoinSymbols();

//        then
        assertThat(Coin.getAllCoinSymbols().size()).isEqualTo(6);
    }

    @Test
    void shouldReturnCoin() {

//        given
        String userInput1 = "5 pln";
        String userInput2 = "2 pln";
        String userInput3 = "1 pln";
        String userInput4 = "0,50 pln";
        String userInput5 = "0,20 pln";
        String userInput6 = "0,10 pln";

//        when
        Coin.matchUserInput(userInput1);
        Coin.matchUserInput(userInput2);
        Coin.matchUserInput(userInput3);
        Coin.matchUserInput(userInput4);
        Coin.matchUserInput(userInput5);
        Coin.matchUserInput(userInput6);

//        then
        assertThat(Coin.matchUserInput(userInput1))
                .isEqualTo(Coin.FIVEZL);
        assertThat(Coin.matchUserInput(userInput2))
                .isEqualTo(Coin.TWOZL);
        assertThat(Coin.matchUserInput(userInput3))
                .isEqualTo(Coin.ONEZL);
        assertThat(Coin.matchUserInput(userInput4))
                .isEqualTo(Coin.FIFTYGR);
        assertThat(Coin.matchUserInput(userInput5))
                .isEqualTo(Coin.TWENTYGR);
        assertThat(Coin.matchUserInput(userInput6))
                .isEqualTo(Coin.TENGR);
    }

    @Test
    void shouldThrowIllegalArgumentException() {

//        given
        String userInput1 = "5pln";
        String userInput2 = "5  pln";
        String userInput3 = "50 pln";
        String userInput4 = "5 pl";
        String userInput5 = "3 pln";

//        then
        assertThrows(IllegalArgumentException.class,
                () -> Coin.matchUserInput(userInput1));
        assertThrows(IllegalArgumentException.class,
                () -> Coin.matchUserInput(userInput2));
        assertThrows(IllegalArgumentException.class,
                () -> Coin.matchUserInput(userInput3));
        assertThrows(IllegalArgumentException.class,
                () -> Coin.matchUserInput(userInput4));
        assertThrows(IllegalArgumentException.class,
                () -> Coin.matchUserInput(userInput5));
    }
}