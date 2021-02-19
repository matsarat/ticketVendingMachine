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

    @Test
    void shouldReturnTrueIfOddMoneyEqualsZero() {

//        given
        given(ticketStorage.getValueOfTicketsInStorage())
                .willReturn(1000);
        given(tempCoinStorage.getValueOfCoinsInStorage())
                .willReturn(1000);

//        when
        ticketVendingMachine.isOddMoneyGivenBackToUser();

//        then
        assertThat(ticketVendingMachine.isOddMoneyGivenBackToUser())
                .isTrue();
    }

    @Test
    void shouldReturnTrueIfValueOfCoinsPreparedToGiveBackEqualsOddMoney() {

//        given

        given(ticketStorage.getValueOfTicketsInStorage())
                .willReturn(1000);
        given(tempCoinStorage.getValueOfCoinsInStorage())
                .willReturn(2000);
        given(ticketVendingMachine.prepareChangeToGiveBack(1000))
                .willReturn(1000);


//        when
        ticketVendingMachine.isOddMoneyGivenBackToUser();

//        then

        then(messagePrinter)
                .should(times(1))
                .printMessage(GIVE_ODD_MONEY);
        then(oddMoneyStorage)
                .should(times(1))
                .giveCoinsBack();
        assertThat(ticketVendingMachine.isOddMoneyGivenBackToUser())
                .isTrue();
    }

    @Test
    void shouldReturnFalseIfOddMoneyNotEqualsZeroAndCoinsPreparedToGiveBackDoNotEqualOddMoney() {


//        given
        given(ticketStorage.getValueOfTicketsInStorage())
                .willReturn(1000);
        given(tempCoinStorage.getValueOfCoinsInStorage())
                .willReturn(2000);
        given(ticketVendingMachine.prepareChangeToGiveBack(1000))
                .willReturn(800).willReturn(900).willReturn(1500);

//        when
        ticketVendingMachine.isOddMoneyGivenBackToUser();

//        then
        then(messagePrinter)
                .should(times(1))
                .printMessage(GIVE_ODD_MONEY_BACK_NOT_POSSIBLE);
        then(tempCoinStorage)
                .should(times(1))
                .giveCoinsBack();
        then(oddMoneyStorage)
                .should(times(1))
                .moveAllCoinsTo(mainCoinStorage);
        assertThat(ticketVendingMachine.isOddMoneyGivenBackToUser())
                .isFalse();
    }


    @Test
    void shouldAskUserForCoinWhenValueOfTicketsIsBiggerThanValueOfCoinsInsertedByUser() {

//        given
        given(tempCoinStorage.getValueOfCoinsInStorage())
                .willReturn(500).willReturn(600);
        given(ticketStorage.getValueOfTicketsInStorage())
                .willReturn(600);

//        when
        ticketVendingMachine.getPaymentFromUser();

//        then
        then(messagePrinter)
                .should(times(1))
                .printMessage(VALUE_OF_TICKETS + ticketStorage.showValueOfTicketsInPLN());
        then(messagePrinter)
                .should(times(1))
                .printMessage(INSERT_COIN);
        then(tempCoinStorage)
                .should(times(1))
                .addCoin(userInputProvider.getCoin());
    }

    @Test
    void shouldReturnTrueIfUserInsertsValidAnswerConfirmingThatAnotherTicketIsNeeded() {

//        given
        given(userInputProvider.getString())
                .willReturn("y");

//        when
        ticketVendingMachine.askIfAnotherTicketNeeded();


//        then
        then(messagePrinter)
                .should(times(1))
                .printMessage(ASK_FOR_ANOTHER_TICKET);
        assertThat(ticketVendingMachine.askIfAnotherTicketNeeded())
                .isTrue();
    }

    @Test
    void shouldReturnFalseIfUserInsertsValidAnswerDenyingThatAnotherTicketIsNeeded() {

//        given
        given(userInputProvider.getString())
                .willReturn("n");

//        when
        ticketVendingMachine.askIfAnotherTicketNeeded();


//        then
        then(messagePrinter)
                .should(times(1))
                .printMessage(ASK_FOR_ANOTHER_TICKET);
        assertThat(ticketVendingMachine.askIfAnotherTicketNeeded())
                .isFalse();
    }

    @Test
    void shouldPrintErrorAndInvokeTheMethodItselfIfUserInsertsInvalidAnswer() {

//        given
        given(userInputProvider.getString())
                .willReturn("Yes")
                .willReturn("Y");

//        when
        ticketVendingMachine.askIfAnotherTicketNeeded();


//        then
        then(messagePrinter)
                .should(times(2))
                .printMessage(ASK_FOR_ANOTHER_TICKET);
        then(messagePrinter)
                .should(times(1))
                .printError(WRONG_ANSWER);
        assertThat(ticketVendingMachine.askIfAnotherTicketNeeded())
                .isTrue();
    }

    @Test
    void shouldAskForAnotherTicketIfUserNeedsOneMore() {

//        given
        given(userInputProvider.getString())
                .willReturn("y")
                .willReturn("n");

//        when
        ticketVendingMachine.getTicketsFromUser();

//        then
        then(messagePrinter)
                .should(times(2))
                .printMessage(ASK_FOR_TICKET);
        then(ticketStorage)
                .should(times(2))
                .addTicket(userInputProvider.getTicket());

    }

    @Test
    void shouldNotAskForAnotherTicketIfUserDoesNotNeedOneMore() {

//        given
        given(userInputProvider.getString())
                .willReturn("n");

//        when
        ticketVendingMachine.getTicketsFromUser();

//        then
        then(messagePrinter)
                .should(times(1))
                .printMessage(ASK_FOR_TICKET);
        then(ticketStorage)
                .should(times(1))
                .addTicket(userInputProvider.getTicket());
    }
}
