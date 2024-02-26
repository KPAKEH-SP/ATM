package ru.denis.atm.Exceptions;

public class UserWithThisEmailAlreadyExists extends Exception {
    public UserWithThisEmailAlreadyExists(){
        super("Ползователь с данной почтой уже зарегистрирован!");
    }
}
