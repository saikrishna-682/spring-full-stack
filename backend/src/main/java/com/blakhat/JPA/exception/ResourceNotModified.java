package com.blakhat.JPA.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class ResourceNotModified extends RuntimeException{

    public ResourceNotModified(String message){
        super(message);
    }
}
