package ru.denis.atm.exceptions.validation;

import ru.denis.atm.exceptions.ValidationException;

public class EmailUniqueException extends ValidationException {
    public EmailUniqueException() {
        super("Ползователь с данной почтой уже зарегистрирован!");
    }
}
