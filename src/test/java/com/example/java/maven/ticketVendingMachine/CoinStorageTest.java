package com.example.java.maven.ticketVendingMachine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.HashMap;


import static org.assertj.core.api.Assertions.assertThat;


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
}
