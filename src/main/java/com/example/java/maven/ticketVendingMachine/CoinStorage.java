package com.example.java.maven.ticketVendingMachine;

import java.util.Map;

public class CoinStorage {
    private final Map<Coin, Integer> mainCoinStorage;
    private final Map<Coin, Integer> tempCoinStorage;
    private final UserInputProvider userInputProvider;


    public CoinStorage(Map<Coin, Integer> mainCoinStorage,
                       Map<Coin, Integer> tempCoinStorage,
                       UserInputProvider userInputProvider) {
        this.mainCoinStorage = mainCoinStorage;
        this.tempCoinStorage = tempCoinStorage;
        this.userInputProvider = userInputProvider;
    }

    public Map<Coin, Integer> getMainCoinStorage() {
        return mainCoinStorage;
    }

    public Map<Coin, Integer> getTempCoinStorage() {
        return tempCoinStorage;
    }


    public static int getValueOfCoinsInStorage(Map<Coin, Integer> coinStorage) {
        int valueOfCoinsInStorage = 0;
        for (Map.Entry<Coin, Integer> coinEntry : coinStorage.entrySet()) {
            valueOfCoinsInStorage += coinEntry.getValue();
        }
        return valueOfCoinsInStorage;
    }

}
