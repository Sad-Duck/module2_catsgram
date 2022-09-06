package ru.yandex.practicum.catsgram.controller;

import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.catsgram.exeption.InvalidEmailException;
import ru.yandex.practicum.catsgram.exeption.UserAlreadyExistException;
import ru.yandex.practicum.catsgram.model.User;

import java.util.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final HashSet<User> users = new HashSet<>();

    @GetMapping
    public HashSet<User> findAll() {
        return users;
    }

    @PostMapping
    public User create(@RequestBody User user) {
        for (User u : users) {
            if (u.getEmail().equals(user.getEmail())) {
                throw new UserAlreadyExistException("User already exists");
            }
        }
        if (user.getEmail() == null) {
            throw new InvalidEmailException("Email already taken");
        } else {
            users.add(user);
        }
        return user;
    }

    @PutMapping
    public User update(@RequestBody User user) {
        for (User u : users) {
            if (u.getEmail().equals(user.getEmail())) {
                users.remove(u);
                users.add(user);
            }
        }
        if (user.getEmail().isEmpty() || user.getEmail().isBlank() || user.getEmail() == null) {
            throw new InvalidEmailException("Email already taken");
        } else {
            users.add(user);
        }
        return user;
        }
    }