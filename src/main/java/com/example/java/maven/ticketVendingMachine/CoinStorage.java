package com.example.java.maven.ticketVendingMachine;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CoinStorage {
    private final Map<Coin, Integer> coins;
    private final MessagePrinter messagePrinter;

    public CoinStorage(Map<Coin, Integer> coins, MessagePrinter messagePrinter) {
        this.coins = coins;
        this.messagePrinter = messagePrinter;
    }
    

    public void clear() {
        coins.clear();
    }

    public void addCoin(Coin coin) {
        int currentNumberOfCoins = Optional.ofNullable(coins.get(coin)).orElse(0);
        coins.put(coin, currentNumberOfCoins + 1);
    }

    public void fillCoinStorageWithGivenNumberOfCoinsForDenomination(int numberOfCoinsForDenomination) {
        for (Coin coin : Coin.values()) {
            for (int i = numberOfCoinsForDenomination; i > 0 ; i--) {
                addCoin(coin);
            }
        }
    }

    public void moveAllCoinsTo(CoinStorage destinationStorage) {
        for (Coin coin : coins.keySet()) {
            int numberOfCoinsInDestinationStorage = destinationStorage.coins.getOrDefault(coin, 0);
            destinationStorage.coins.put(coin, numberOfCoinsInDestinationStorage + (coins.get(coin)));
        }
        coins.clear();
    }

    public int getValueOfCoinsInStorage() {
        int valueOfCoinsInStorage = 0;
        for (Coin coin : coins.keySet()) {
            valueOfCoinsInStorage += (coin.getCoinValue() * coins.get(coin));
        }
        return valueOfCoinsInStorage;
    }

    public void moveRequiredNumberOfCoins(CoinStorage destinationStorage, int requiredNumberOfCoins, Coin coin) {
        int currentNumberOfCoinsInMainCoinStorage = coins.getOrDefault(coin, 0);
        if (currentNumberOfCoinsInMainCoinStorage > requiredNumberOfCoins) {
            coins.put(coin, currentNumberOfCoinsInMainCoinStorage - requiredNumberOfCoins);
            destinationStorage.coins.put(coin, requiredNumberOfCoins);
        }
        else if (currentNumberOfCoinsInMainCoinStorage <= requiredNumberOfCoins && currentNumberOfCoinsInMainCoinStorage > 0) {
            destinationStorage.coins.put(coin, coins.remove(coin));
        }
    }

    public void giveCoinsBack() {
        for (Map.Entry<Coin, Integer> coinEntry : coins.entrySet()) {
            int numberOfCoinsToGiveBack = coinEntry.getValue();
            while (numberOfCoinsToGiveBack > 0) {
                messagePrinter.printMessage(coinEntry.getKey().toString());
                numberOfCoinsToGiveBack -= 1;
            }
        }
        clear();
    }
}
