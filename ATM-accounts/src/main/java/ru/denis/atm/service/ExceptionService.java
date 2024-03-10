package ru.denis.atm.service;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.denis.atm.exceptions.UserWithThisEmailAlreadyExists;
import ru.denis.atm.exceptions.UserWithThisIdNotExist;
import ru.denis.atm.exceptions.UserWithThisLoginAlreadyExists;

@ControllerAdvice
public class ExceptionService extends ResponseEntityExceptionHandler {
    @ExceptionHandler({UserWithThisIdNotExist.class, UserWithThisLoginAlreadyExists.class, UserWithThisEmailAlreadyExists.class})
    public ResponseEntity<Object> idException(Exception e, WebRequest request){
        return handleExceptionInternal(e, e.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}
