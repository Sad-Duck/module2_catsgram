package ru.yandex.practicum.catsgram.exeption;

public class InvalidEmailException extends IllegalStateException {
    public InvalidEmailException(String message) {
        super(message);
    }
}