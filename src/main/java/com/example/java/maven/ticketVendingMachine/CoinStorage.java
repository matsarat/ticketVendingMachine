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

    public static int getValueOfCoinsInStorage(Map<Coin, Integer> coinStorage) {
        int valueOfCoinsInStorage = 0;
        for (Coin coin : coinStorage.keySet()) {
            valueOfCoinsInStorage += (coin.getCoinValue() * coinStorage.get(coin));
        }
        return valueOfCoinsInStorage;
    }
}
