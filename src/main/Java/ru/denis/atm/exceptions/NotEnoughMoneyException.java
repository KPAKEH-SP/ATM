package ru.denis.atm.exceptions;

public class NotEnoughMoneyException extends Exception {

    @Override
    public String getMessage() {
        return "Банкомат не может выдать данную сумму!";
    }

    public NotEnoughMoneyException() {
        System.out.println(getMessage());
    }
}
