package com.example.multidbspringboot.service;

import com.example.multidbspringboot.entity.user.User;
import com.example.multidbspringboot.repository.user.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public User saveUser(@RequestBody User user) {
        return userRepo.save(user);
    }

}
