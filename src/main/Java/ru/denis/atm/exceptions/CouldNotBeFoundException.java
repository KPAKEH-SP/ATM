package ru.denis.atm.exceptions;

public class CouldNotBeFoundException extends Exception {

    @Override
    public String getMessage() {
        return "Не удалось найти данный элемент";
    }

    public CouldNotBeFoundException() {
        System.out.println(getMessage());
    }
}
