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
        for (Map.Entry<Coin, Integer> coin : tempCoinStorage.getCoins().entrySet()) {
            int numberOfCoinsInTempStorage = coin.getValue();
            int numberOfCoinsInMainStorage = Optional.ofNullable(mainCoinStorage.getCoins().get(coin.getValue())).orElse(0);
            mainCoinStorage.getCoins().put(coin.getKey(), numberOfCoinsInMainStorage + numberOfCoinsInTempStorage);

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
        if (getNumberOfCoinsWithGivenValueInMainCoinStorage(coinValue, mainCoinStorage) >= requiredNumberOfCoins) {
            return true;
        }
        else {
            return false;
        }
    }

    public static int getNumberOfCoinsWithGivenValueInMainCoinStorage(int coinValue, CoinStorage mainCoinStorage) {
        int numberOfCoins = 0;
        for (Map.Entry<Coin, Integer> coinEntry : mainCoinStorage.getCoins().entrySet()) {
            if (coinEntry.getKey().getCoinValue() == coinValue) {
                numberOfCoins = coinEntry.getValue();
            }
        }
        return numberOfCoins;
    }

    public static void moveRequiredNumberOfCoinsFromMainStorageToOddMoneyStorage(CoinStorage mainCoinStorage, CoinStorage oddMoneyStorage, int requiredNumberOfCoins, Coin coin) {
        int currentNumberOfCoinsInMainCoinStorage = mainCoinStorage.getCoins().get(coin);
        mainCoinStorage.getCoins().put(coin, currentNumberOfCoinsInMainCoinStorage - requiredNumberOfCoins);
        oddMoneyStorage.getCoins().put(coin, requiredNumberOfCoins);
    }

    public void giveCoinsBack(CoinStorage oddMoneyStorage) {
        for (Map.Entry<Coin, Integer> coinEntry : oddMoneyStorage.getCoins().entrySet()) {
            int numberOfCoinsToGiveBack = coinEntry.getValue();
            while (numberOfCoinsToGiveBack > 0) {
                messagePrinter.printMessage(coinEntry.getKey().toString());
                numberOfCoinsToGiveBack -= 1;
            }
        }
        oddMoneyStorage.clear();
    }
}
