package com.andresvasquez.demoproject.repository;

import com.andresvasquez.demoproject.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private static UserRepository instance = new UserRepository();
    private static List<User> users = new ArrayList<>();

    public static UserRepository getInstance() {
        return instance;
    }

    private UserRepository() {
        fillDefaultValues();
    }

    public User login(String email, String password) {
        for (User user : users) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    private void fillDefaultValues() {
        users.add(new User("Administrador",
                "admin@app.com",
                "123",
                "Bolivia"));
    }
}

