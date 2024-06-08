package ru.heumn.paymentservice.storages.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class RuntimeException extends Exception {
    public RuntimeException(String message){
        super(message);
    }
}
