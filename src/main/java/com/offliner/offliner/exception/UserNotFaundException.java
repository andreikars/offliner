package com.offliner.offliner.exception;

public class UserNotFaundException extends RuntimeException{
    public UserNotFaundException(Long id){
        super("Could not faund the user with id" + id);
    }
}
