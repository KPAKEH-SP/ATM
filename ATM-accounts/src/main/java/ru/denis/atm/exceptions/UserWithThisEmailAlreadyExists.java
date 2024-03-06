package ru.denis.atm.exceptions;

public class UserWithThisEmailAlreadyExists extends Exception {
    public UserWithThisEmailAlreadyExists(){
        super("Ползователь с данной почтой уже зарегистрирован!");
    }
}
