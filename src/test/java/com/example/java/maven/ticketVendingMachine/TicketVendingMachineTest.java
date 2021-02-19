package com.example.java.maven.ticketVendingMachine;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class TicketVendingMachineTest {
    private final static String ASK_FOR_ANOTHER_TICKET = "Do you need another ticket? Insert Y for YES or N for NO.";
    private final static String WRONG_ANSWER = "Wrong answer! Insert Y for YES or N for NO.";
    private final static String ASK_FOR_TICKET = "Which ticket would you like to buy? Available tickets are: " + Ticket.getAllTicketSymbols();
    private final static String INSERT_COIN = "Insert coin. Accepted coins are: " + Coin.getAllCoinSymbols();
    private final static String GIVE_ODD_MONEY = "Here is your change";
    private final static String GIVE_ODD_MONEY_BACK_NOT_POSSIBLE = "Impossible to give back odd money. Exact change is required. Here are your coins:";
    private final static String VALUE_OF_TICKETS = "Value of your tickets is: ";

    MessagePrinter messagePrinter = Mockito.mock(MessagePrinter.class);
    UserInputProvider userInputProvider = Mockito.mock(UserInputProvider.class);

    TicketStorage ticketStorage = Mockito.mock(TicketStorage.class);
    CoinStorage mainCoinStorage = Mockito.mock(CoinStorage.class);
    CoinStorage tempCoinStorage = Mockito.mock(CoinStorage.class);
    CoinStorage oddMoneyStorage = Mockito.mock(CoinStorage.class);

    TicketVendingMachine ticketVendingMachine;

    @BeforeEach
    void setUp() {
        this.ticketVendingMachine =
                new TicketVendingMachine(ticketStorage,
                        mainCoinStorage,
                        tempCoinStorage,
                        oddMoneyStorage,
                        messagePrinter,
                        userInputProvider);

    }


}
