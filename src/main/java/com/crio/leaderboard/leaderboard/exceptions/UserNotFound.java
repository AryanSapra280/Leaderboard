package com.crio.leaderboard.leaderboard.exceptions;

public class UserNotFound extends RuntimeException{
    public UserNotFound() {
        super();
    }
    public UserNotFound(String message) {
        super(message);
    }
}
