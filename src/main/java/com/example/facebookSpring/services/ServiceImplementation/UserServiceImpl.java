package com.example.facebookSpring.services.ServiceImplementation;

import com.example.facebookSpring.model.Users;
import com.example.facebookSpring.repository.UserRepository;
import com.example.facebookSpring.services.UserInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserInterface {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<Users> getAllUsers(){
        return userRepository.findAll();
    }

    public boolean addUser(Users user){
        Optional<Users> usersOptional = userRepository.findByEmail(user.getEmail());
        if(usersOptional.isPresent()){
            return true;
        }
        userRepository.save(user);
        return false;
    }

    public String validateUser(Users user) {
        Optional<Users> user1 = userRepository.findByEmail(user.getEmail());
        if (user1.isEmpty()) {
            return "Not Registered Email";
        }

        if (!user1.get().getPassword().equals(user.getPassword())) {
            return "Incorrect Password";
        }

        System.out.println("done");
        return "approved";
    }

    public Optional<Users> getUserByEmail(String email){
        return userRepository.findByEmail(email);
    }
}
