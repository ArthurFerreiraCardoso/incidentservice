package com.incidentsservice.diazero.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class IncidentException extends RuntimeException {

    @Getter
    private HttpStatus httpStatus;
    private String mensagem;

    public IncidentException(String msg, HttpStatus status){

        super(msg);
        this.httpStatus = status;
    }

    public IncidentException exceptionMethodImpl(String mesage, HttpStatus status){

        this.mensagem = mesage;
        this.httpStatus = status;
        return null;
    }
}
