package ru.denis.atm.exceptions;

public class UserWithThisIdNotExist extends Exception {
    public UserWithThisIdNotExist() {
        super("Пользователя с данным id не существует!");
    }
}
