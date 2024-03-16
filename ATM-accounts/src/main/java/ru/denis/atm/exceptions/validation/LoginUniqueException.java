package ru.denis.atm.exceptions.validation;

import ru.denis.atm.exceptions.ValidationException;

public class LoginUniqueException extends ValidationException {
    public LoginUniqueException() {
        super("Пользователь с данным логином уже зарегистрирован!");
    }
}
