package com.example.java.maven.ticketVendingMachine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.BDDMockito.then;

import java.util.Arrays;
import java.util.HashMap;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;


public class CoinStorageTest {
    MessagePrinter messagePrinter = Mockito.mock(MessagePrinter.class);

    CoinStorage primaryCoinStorage;
    CoinStorage destinationCoinStorage;


    @BeforeEach
    void createCoinStorages() {
        this.primaryCoinStorage = new CoinStorage(new HashMap<>(), messagePrinter);
        this.destinationCoinStorage = new CoinStorage(new HashMap<>(), messagePrinter);
    }


    @Test
    void shouldAddCoin() {
        Coin coin = Coin.ONEZL;

//        when
        primaryCoinStorage.addCoin(coin);

//        then
        assertThat(primaryCoinStorage.getCoins().size()).isEqualTo(1);
    }

    @Test
    void shouldFillCoinStorageWithGivenNumberOfCoinsForDenomination() {

//        when
        primaryCoinStorage.fillCoinStorageWithGivenNumberOfCoinsForDenomination(1);

//        then
        assertThat(primaryCoinStorage.getCoins().size()).isEqualTo(6);
        assertThat(primaryCoinStorage.getValueOfCoinsInStorage()).isEqualTo(880);
    }

    @Test
    void shouldMoveAllCoinsFromPrimaryToDestinationStorage() {

//        given
        primaryCoinStorage.fillCoinStorageWithGivenNumberOfCoinsForDenomination(1);

//        when
        primaryCoinStorage.moveAllCoinsTo(destinationCoinStorage);

//        then
        assertThat(primaryCoinStorage.getCoins().size()).isEqualTo(0);
        assertThat(primaryCoinStorage.getValueOfCoinsInStorage()).isEqualTo(0);

        assertThat(destinationCoinStorage.getCoins().size()).isEqualTo(6);
        assertThat(destinationCoinStorage.getValueOfCoinsInStorage()).isEqualTo(880);
    }

    @Test
    void shouldGiveCoinsBackToUser() {

//        given
        primaryCoinStorage.fillCoinStorageWithGivenNumberOfCoinsForDenomination(1);

//        when
        primaryCoinStorage.giveCoinsBack();

//        then
        assertThat(primaryCoinStorage.getCoins().size()).isEqualTo(0);

        for (String s : Arrays.asList("Coin 5 PLN", "Coin 2 PLN", "Coin 1 PLN", "Coin 0,50 PLN", "Coin 0,20 PLN", "Coin 0,10 PLN")) {
            then(messagePrinter).should(times(1)).printMessage(s);
        }
    }
}
