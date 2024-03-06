package ru.denis.atm.exceptions;

public class UserWithThisLoginAlreadyExists extends Exception {
    public UserWithThisLoginAlreadyExists(){
        super("Ползователь с данным логином уже зарегистрирован!");
    }
}
