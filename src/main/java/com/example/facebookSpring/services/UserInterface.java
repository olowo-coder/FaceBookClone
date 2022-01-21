package com.example.facebookSpring.services;

import com.example.facebookSpring.model.Users;

import java.util.List;
import java.util.Optional;

public interface UserInterface {
    List<Users> getAllUsers();

    boolean addUser(Users user);

    String validateUser(Users user);

    Optional<Users> getUserByEmail(String email);
}
