package com.example.java.maven.ticketVendingMachine;


import java.util.HashMap;
import java.util.Map;

public class TicketVendingMachine {
    private final TicketPrinter ticketPrinter;
    private final TicketStorage ticketStorage;
    private final CoinStorage coinStorage;
    private final MessagePrinter messagePrinter;
    private final UserInputProvider userInputProvider;

    public TicketVendingMachine(TicketPrinter ticketPrinter,
                                TicketStorage ticketStorage,
                                CoinStorage coinStorage,
                                MessagePrinter messagePrinter,
                                UserInputProvider userInputProvider) {

        this.ticketPrinter = ticketPrinter;
        this.ticketStorage = ticketStorage;
        this.coinStorage = coinStorage;
        this.messagePrinter = messagePrinter;
        this.userInputProvider = userInputProvider;

    }

    public Coin getCoin() {
        String userInput = userInputProvider.getString();
        try {
            return Coin.matchUserInput(userInput.toUpperCase());
        } catch (IllegalArgumentException exception) {
            messagePrinter.printError(exception.getMessage());
            return getCoin();
        }
    }

    public Ticket getTicket() {
        String chosenTicket = userInputProvider.getString();
        try {
            return Ticket.matchUserInput(chosenTicket.toUpperCase());
        } catch (IllegalArgumentException exception) {
            messagePrinter.printError(exception.getMessage());
            return getTicket();
        }
    }

    public void addCoinToTempCoinStorage() {
        coinStorage.getTempCoinStorage().put(getCoin(), getCoin().getValue());
    }

    public void moveCoinsFromTempToMainCoinStorage() {
        coinStorage.getMainCoinStorage().putAll(coinStorage.getTempCoinStorage());
    }

    public void giveBackOddMoney() {
        //reszta do wydania
        Map<Coin, Integer> oddCoinsToGiveBackStorage = new HashMap<>();

        //cena wszystkich biletow zamowionych przez usera
        int ticketsPrice = ticketStorage.getValueOfTicketsInStorage();

        //ilosc hajsu jaka czlowiek wrzucil do automatu
        int valueOfCoinsInTempStorage = CoinStorage.getValueOfCoinsInStorage(coinStorage.getTempCoinStorage());

        //reszta do wydania
        int oddMoney = valueOfCoinsInTempStorage - ticketsPrice;
        while (oddMoney != 0) {
            for (Coin coin : Coin.values()) {
                int requiredNumberOfCoinsWithGivenValue = getRequiredNumberOfCoinsWithGivenValue(oddMoney, coin.getValue());
                if (coinStorage.hasNumberOfCoins(coin, requiredNumberOfCoinsWithGivenValue)){

                }
            }
            for (int value : Coin.getAllCoinValues()) {
                int requiredNumberOfCoinsWithGivenValue = getRequiredNumberOfCoinsWithGivenValue(oddMoney, value);
                if (requiredNumberOfCoinsWithGivenValue > 0) {
                    if (areRequiredCoinsAvailableInCoinStorage(requiredNumberOfCoinsWithGivenValue, value)) {
                        getCoinFromCoinStorageToGiveBack(requiredNumberOfCoinsWithGivenValue, value, oddCoinsToGiveBackStorage);
                        int valueOfCoinsToGiveBackStorage = getValueOfCoinsToGiveBackStorage(oddCoinsToGiveBackStorage);
                    }
                }
            }
        }
    }

    public int getValueOfCoinsToGiveBackStorage(Map<Coin, Integer> oddCoinsToGiveBack) {
        int valueOfCoinsToGiveBackStorage = 0;
        for (Map.Entry<Coin, Integer> coinEntry : oddCoinsToGiveBack.entrySet()) {
            valueOfCoinsToGiveBackStorage += coinEntry.getValue();
        }
        return valueOfCoinsToGiveBackStorage;
    }

    public void getCoinFromCoinStorageToGiveBack(int numberOfCoinsWithGivenValue, int value, Map<Coin, Integer> oddCoinsToGiveBackStorage) {
        for (int i = 0; i < numberOfCoinsWithGivenValue; i++) {
            for (Map.Entry<Coin, Integer> coinEntry : coinStorage.getMainCoinStorage().entrySet()) {
                if (coinEntry.getValue() == value) {
                    oddCoinsToGiveBackStorage.put(coinEntry.getKey(), coinEntry.getValue());
                    coinStorage.getMainCoinStorage().entrySet().remove(coinEntry);
                }
            }
        }
    }

    public int getRequiredNumberOfCoinsWithGivenValue(int oddMoney, int coinValue) {
        return Math.floorDiv(oddMoney, coinValue);
    }

    public boolean areRequiredCoinsAvailableInCoinStorage(int requiredNumberOfCoins, int coinValue) {
        if (getNumberOfCoinsWithGivenValueInMainCoinStorage(coinValue) >= requiredNumberOfCoins) {
            return true;
        }
        else {
            return false;
        }
    }

    //todo: wszystkie metody zwiazane ze storeage do storega
    public int getNumberOfCoinsWithGivenValueInMainCoinStorage(int coinValue) {
        int coinCounter = 0;
        for (Map.Entry<Coin, Integer> coinEntry : coinStorage.getMainCoinStorage().entrySet()) {
            if (coinEntry.getValue() == coinValue) {
                coinCounter++;
            }
        }
        return coinCounter;
    }

    public void getPayment() {
        while (ticketStorage.getValueOfTicketsInStorage() > CoinStorage.getValueOfCoinsInStorage(coinStorage.getTempCoinStorage())) {
            addCoinToTempCoinStorage();
        }
    }
}
