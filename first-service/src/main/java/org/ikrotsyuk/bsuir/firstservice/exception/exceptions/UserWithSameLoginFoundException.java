package org.ikrotsyuk.bsuir.firstservice.exception.exceptions;

public class UserWithSameLoginFoundException extends RuntimeException {
    public UserWithSameLoginFoundException(String message){
        super(message);
    }
}
