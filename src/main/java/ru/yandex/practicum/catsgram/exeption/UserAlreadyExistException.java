package ru.yandex.practicum.catsgram.exeption;

public class UserAlreadyExistException extends IllegalStateException {
    public UserAlreadyExistException(String message) {
        super(message);
    }
}
