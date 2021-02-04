package com.example.java.maven.ticketVendingMachine;


public class TicketVendingMachine {
    private final static String ASK_FOR_ANOTHER_TICKET = "Do you need another ticket? Insert Y for YES or N for NO.";
    private final static String WRONG_ANSWER = "Wrong answer! Insert Y for YES or N for NO.";
    private final static String ASK_FOR_TICKET = "Which ticket would you like to buy? Available tickets are: " + Ticket.getAllTicketSymbols();
    private final static String INSERT_COIN = "Insert coin. Accepted coins are: " + Coin.getAllCoinSymbols();
    private final static String GIVE_ODD_MONEY = "Here is your change";
    private final static String VALUE_OF_TICKETS = "Value of your tickets is: ";



    private final TicketPrinter ticketPrinter;
    private final TicketStorage ticketStorage;
    private final CoinStorage mainCoinStorage;
    private final CoinStorage tempCoinStorage;
    private final CoinStorage oddMoneyStorage;
    private final MessagePrinter messagePrinter;
    private final UserInputProvider userInputProvider;

    public TicketVendingMachine(TicketPrinter ticketPrinter,
                                TicketStorage ticketStorage,
                                CoinStorage mainCoinStorage,
                                CoinStorage tempCoinStorage,
                                CoinStorage oddMoneyStorage,
                                MessagePrinter messagePrinter,
                                UserInputProvider userInputProvider) {

        this.ticketPrinter = ticketPrinter;
        this.ticketStorage = ticketStorage;
        this.mainCoinStorage = mainCoinStorage;
        this.tempCoinStorage = tempCoinStorage;
        this.oddMoneyStorage = oddMoneyStorage;
        this.messagePrinter = messagePrinter;
        this.userInputProvider = userInputProvider;
    }

    public void fillMainCoinStorage() {
        for (Coin coin : Coin.values()) {
            mainCoinStorage.getCoins().put(coin, 10);
        }
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
    public void giveBackOddMoney() {
        int ticketsPrice = ticketStorage.getValueOfTicketsInStorage();
        int valueOfCoinsInTempStorage = tempCoinStorage.getValueOfCoinsInStorage();
        int oddMoney = valueOfCoinsInTempStorage - ticketsPrice;
        int valueOfCoinsToGiveBackStorage = 0;
        for (Coin coin : Coin.values()) {
            if (valueOfCoinsToGiveBackStorage != oddMoney) {
                int requiredNumberOfGivenCoins = CoinStorage.getRequiredNumberOfCoinsWithGivenValue(oddMoney, coin.getCoinValue());
                if (requiredNumberOfGivenCoins > 0) {
                    if (CoinStorage.areRequiredCoinsAvailableInCoinStorage(requiredNumberOfGivenCoins, coin.getCoinValue(), mainCoinStorage)) {
                        CoinStorage.moveRequiredNumberOfCoinsFromMainStorageToOddMoneyStorage(mainCoinStorage, oddMoneyStorage, requiredNumberOfGivenCoins, coin);
                        valueOfCoinsToGiveBackStorage = oddMoneyStorage.getValueOfCoinsInStorage();
                    }
                }
            }
        }
        messagePrinter.printMessage(GIVE_ODD_MONEY);
        oddMoneyStorage.giveCoinsBack(oddMoneyStorage);
    }

    public void getPaymentFromUser() {
        messagePrinter.printMessage(VALUE_OF_TICKETS + ticketStorage.showValueOfTicketsInPLN());
        while (ticketStorage.getValueOfTicketsInStorage() > tempCoinStorage.getValueOfCoinsInStorage()) {
            messagePrinter.printMessage(INSERT_COIN);
            tempCoinStorage.addCoin(getCoin());
        }
    }

    public void getTicketsFromUser() {
        messagePrinter.printMessage(ASK_FOR_TICKET);
        ticketStorage.addTicket(getTicket());
        while (askIfAnotherTicketNeeded()) {
            messagePrinter.printMessage(ASK_FOR_TICKET);
            ticketStorage.addTicket(getTicket());
        }
    }

    public boolean askIfAnotherTicketNeeded() {
        messagePrinter.printMessage(ASK_FOR_ANOTHER_TICKET);
        String userAnswer = userInputProvider.getString();
        if (userAnswer.equalsIgnoreCase("Y")) {
            return true;
        }
        else if (userAnswer.equalsIgnoreCase("N")) {
            return false;
        }
        else {
            messagePrinter.printError(WRONG_ANSWER);
            return askIfAnotherTicketNeeded();
        }
    }
}
