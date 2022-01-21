package com.example.facebookSpring.repository;

import com.example.facebookSpring.model.Users;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void addUser(){
        Users user = Users.builder()
                .userId(1L)
                .firstName("Henry")
                .lastName("Samuel")
                .email("henry@email.com")
                .phone("09034521345")
                .password("henry124")
                .build();

        userRepository.save(user);
    }

    @Test
    public void getAllUsers() {
        List<Users> usersList = userRepository.findAll();
        System.out.println("UserList -> " + usersList);
    }

    @Test
    public void getUserByEmail(){
        Optional<Users> usersOptional = userRepository.findByEmail("henry@email.com");
        System.out.println("user -> " + usersOptional.get());
    }



}