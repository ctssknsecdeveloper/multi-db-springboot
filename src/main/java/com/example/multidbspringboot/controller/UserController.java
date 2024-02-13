package com.example.multidbspringboot.controller;

import com.example.multidbspringboot.entity.user.User;
import com.example.multidbspringboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

   /* @Autowired
    private UserRepo userRepo;
*/
    @Autowired
    private UserService userService;

    @PostMapping
    public User saveUser(@RequestBody User user) {
        return /*userRepo.save(user);*/ userService.saveUser(user);
    }

   /* @GetMapping
    public List<User> getAllUser(){
        return userRepo.findAll();
    }*/
}
