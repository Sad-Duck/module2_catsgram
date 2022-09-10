package ru.yandex.practicum.catsgram.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.catsgram.exeption.InvalidEmailException;
import ru.yandex.practicum.catsgram.exeption.UserAlreadyExistException;
import ru.yandex.practicum.catsgram.model.User;

import java.util.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    private final HashSet<User> users = new HashSet<>();

    @GetMapping
    public HashSet<User> findAll() {
        log.info("текущее количество пользователей: " + users.size());
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
        log.info("добавляем пользователя " + user);
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