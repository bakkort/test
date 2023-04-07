package com.example.Gifts.service;

import com.example.Gifts.exception.user.UserAlreadyExistsException;
import com.example.Gifts.exception.user.UserNotFoundException;
import com.example.Gifts.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    public static List<User> userDataBase = new ArrayList<>();

    public static List<User> addUser(User user) throws UserAlreadyExistsException {
        var idUnique = userDataBase.stream()
                .filter(s -> s.getId() == user.getId())
                .findFirst()
                .orElse(null);

        if (idUnique == null) {
            userDataBase.add(user);
            return userDataBase;
        }
        throw new UserAlreadyExistsException("User already exists in database");
    }

    public static User getUser(int id) {
        return userDataBase.stream()
                .filter(s -> s.getId().intValue() == id)
                .findFirst()
                .orElse(null);
    }

    public static List<User> updateUser(User user) throws UserNotFoundException {
        User currentUser = getUser(user.getId().intValue());
        if (currentUser != null) {
            userDataBase.set(userDataBase.indexOf(currentUser), user);
            return userDataBase;
        }
        throw new UserNotFoundException("Gift not found in the database");
    }

    public static List<User> deleteUser(int id) throws UserNotFoundException {
        User user = getUser(id);
        if (user != null) {
            userDataBase.remove(user);
            return userDataBase;
        }
        throw new UserNotFoundException("Gift not found in the database");
    }
}
