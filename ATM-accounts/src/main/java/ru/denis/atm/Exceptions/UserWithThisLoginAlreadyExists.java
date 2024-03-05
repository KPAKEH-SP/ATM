package ru.denis.atm.Exceptions;

public class UserWithThisLoginAlreadyExists extends Exception {
    public UserWithThisLoginAlreadyExists(){
        super("Ползователь с данным логином уже зарегистрирован!");
    }
}
