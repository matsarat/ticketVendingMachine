package com.example.java.maven.ticketVendingMachine;

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
        return coins;
    }

    public void clear() {
        coins.clear();
    }

    public void addCoin(Coin coin) {
        int currentNumberOfCoins = Optional.ofNullable(coins.get(coin)).orElse(0);
        coins.put(coin, currentNumberOfCoins + 1);
    }

    public static void moveCoinsFromTempToMainCoinStorage(CoinStorage mainCoinStorage, CoinStorage tempCoinStorage) {
        for (Coin coin : Coin.values()) {
            if (tempCoinStorage.coins.keySet().contains(coin)) {
                int numberOfGivenCoinsInTempStorage = getNumberOfCoinsWithGivenValueInGivenCoinStorage(coin.getCoinValue(), tempCoinStorage);
                int numberOfGivenCoinsInMainStorage = getNumberOfCoinsWithGivenValueInGivenCoinStorage(coin.getCoinValue(), mainCoinStorage);
                mainCoinStorage.coins.put(coin, numberOfGivenCoinsInMainStorage + numberOfGivenCoinsInTempStorage);
            }
        }
        tempCoinStorage.clear();
    }

    public int getValueOfCoinsInStorage() {
        int valueOfCoinsInStorage = 0;
        for (Coin coin : coins.keySet()) {
            valueOfCoinsInStorage += (coin.getCoinValue() * coins.get(coin));
        }
        return valueOfCoinsInStorage;
    }

    public static int getRequiredNumberOfCoinsWithGivenValue(int oddMoney, int coinValue) {
        return Math.floorDiv(oddMoney, coinValue);
    }

    public static boolean areRequiredCoinsAvailableInCoinStorage(int requiredNumberOfCoins, int coinValue, CoinStorage mainCoinStorage) {
        if (getNumberOfCoinsWithGivenValueInGivenCoinStorage(coinValue, mainCoinStorage) >= requiredNumberOfCoins) {
            return true;
        }
        else {
            return false;
        }
    }

    public static int getNumberOfCoinsWithGivenValueInGivenCoinStorage(int coinValue, CoinStorage givenCoinStorage) {
        int numberOfCoins = 0;
        for (Map.Entry<Coin, Integer> coinEntry : givenCoinStorage.coins.entrySet()) {
            if (coinEntry.getKey().getCoinValue() == coinValue) {
                numberOfCoins = coinEntry.getValue();
            }
        }
        return numberOfCoins;
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
