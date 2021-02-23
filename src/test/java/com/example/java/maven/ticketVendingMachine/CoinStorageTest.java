package com.example.java.maven.ticketVendingMachine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;


public class CoinStorageTest {
    CoinStorage primaryCoinStorage;

    @BeforeEach
    void createCoinStorage() {
        this.primaryCoinStorage = new CoinStorage(new HashMap<>());
    }


    @Test
    void shouldAddCoin() {
        Coin coin = Coin.ONEZL;

//        when
        primaryCoinStorage.addCoin(coin);

//        then
        assertThat(primaryCoinStorage.getCoins().get(coin)).isEqualTo(1);
        assertThat(primaryCoinStorage.getCoins().size()).isEqualTo(1);
        assertThat(primaryCoinStorage.getValueOfCoinsInStorage()).isEqualTo(100); // 100, because ONEZL coin is represented by int value expressed in groszs (1 PLN = 100 gr)

    }

    @Test
    void shouldFillCoinStorageWithGivenNumberOfCoinsForDenomination() {

//        when
        primaryCoinStorage.fillCoinStorageWithGivenNumberOfCoinsForDenomination(1);

//        then
        assertThat(primaryCoinStorage.getCoins().size()).isEqualTo(6); // 6, because we have 6 denominations and we expect one coin of a kind to be in storage
        assertThat(primaryCoinStorage.getValueOfCoinsInStorage()).isEqualTo(880); // if we take one coin of a kind and sum up values, then it would be 880 groszs
    }

    @Test
    void shouldMoveAllCoinsFromPrimaryToDestinationStorage() {

        CoinStorage destinationCoinStorage = new CoinStorage(new HashMap<>());

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
        Map<Coin, Integer> coins =  primaryCoinStorage.giveCoinsBack();

//        then
        assertThat(primaryCoinStorage.getCoins().size()).isEqualTo(0);

        for (Coin coin : Coin.values()) {
            assertThat(coins.get(coin)).isEqualTo(1);
        }
    }
}
