package com.incidentsservice.diazero.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class IncidentException extends RuntimeException {

    @Getter
    private HttpStatus httpStatus;

    public IncidentException(String msg, HttpStatus status){

        super(msg);
        this.httpStatus = status;
    }
}
