package ru.yandex.practicum.catsgram.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.catsgram.controller.UserController;
import ru.yandex.practicum.catsgram.exeption.InvalidEmailException;
import ru.yandex.practicum.catsgram.exeption.UserAlreadyExistException;
import ru.yandex.practicum.catsgram.model.User;

import java.util.HashSet;

@Service
public class UserService {
    private final HashSet<User> users = new HashSet<>();
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    public HashSet<User> findAll() {
        log.info("текущее количество пользователей: " + users.size());
        return users;
    }

    public User create(User user) {
        for (User u : users) {
            if (u.getEmail().equals(user.getEmail())) {
                throw new UserAlreadyExistException("User already exists");
            }
        }
        if (user.getEmail() == null) {
            throw new InvalidEmailException("Email already taken");
        } else {
            log.info("добавляем пользователя " + user);
            users.add(user);
        }
        return user;
    }

    public User update(User user) {
        for (User u : users) {
            if (u.getEmail().equals(user.getEmail())) {
                users.remove(u);
                users.add(user);
            }
        }
        if (user.getEmail().isEmpty() || user.getEmail().isBlank() || user.getEmail() == null) {
            throw new InvalidEmailException("Email already taken");
        } else {
            log.info("обновляем пользователя " + user);
            users.add(user);
        }
        return user;
    }

}
