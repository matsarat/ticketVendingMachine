package com.example.java.maven.ticketVendingMachine;

import java.util.Map;
import java.util.Optional;

public class CoinStorage {
    private final Map<Coin, Integer> coins;

    public CoinStorage(Map<Coin, Integer> coins) {
        this.coins = coins;
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

    public static int getValueOfCoinsInStorage(CoinStorage coinStorage) {
        int valueOfCoinsInStorage = 0;
        for (Coin coin : coinStorage.getCoins().keySet()) {
            valueOfCoinsInStorage += (coin.getCoinValue() * coinStorage.getCoins().get(coin));
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
}
