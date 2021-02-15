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

    public Map<Coin, Integer> getCoins() {
        return new HashMap<>(coins);
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

    public void moveCoinsTo(CoinStorage destinationStorage) {
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

    public static boolean areRequiredCoinsAvailableInCoinStorage(int requiredNumberOfCoins, Coin coin, CoinStorage mainCoinStorage) {
        if (mainCoinStorage.coins.get(coin) >= requiredNumberOfCoins) {
            return true;
        }
        else {
            return false;
        }
    }

    public static void moveRequiredNumberOfCoinsFromMainStorageToOddMoneyStorage(CoinStorage mainCoinStorage, CoinStorage oddMoneyStorage, int requiredNumberOfCoins, Coin coin) {
        int currentNumberOfCoinsInMainCoinStorage = mainCoinStorage.coins.get(coin);
        mainCoinStorage.coins.put(coin, currentNumberOfCoinsInMainCoinStorage - requiredNumberOfCoins);
        oddMoneyStorage.coins.put(coin, requiredNumberOfCoins);
    }

    public void giveCoinsBack(CoinStorage oddMoneyStorage) {
        for (Map.Entry<Coin, Integer> coinEntry : oddMoneyStorage.coins.entrySet()) {
            int numberOfCoinsToGiveBack = coinEntry.getValue();
            while (numberOfCoinsToGiveBack > 0) {
                messagePrinter.printMessage(coinEntry.getKey().toString());
                numberOfCoinsToGiveBack -= 1;
            }
        }
        oddMoneyStorage.clear();
    }
}
