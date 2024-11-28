package com.offliner.offliner.exception;

public class UserNotFaundUsernameException extends RuntimeException {
    // Теперь принимает String (имя пользователя)
    public UserNotFaundUsernameException(String username) {
        super("Could not find the user with username: " + username);
    }
}
