package ru.denis.atm.service;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.denis.atm.exceptions.ValidationException;
import ru.denis.atm.exceptions.UserWithThisIdNotExist;

@ControllerAdvice
public class ExceptionService extends ResponseEntityExceptionHandler {
    @ExceptionHandler({ValidationException.class, UserWithThisIdNotExist.class})
    public ResponseEntity<Object> exceptionHandler(Exception e, WebRequest request) {
        return handleExceptionInternal(e, e.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}
