package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.security.services.UserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Data
@RestController
@RequestMapping("api/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("")
    public Iterable<User> listUser(){
        return userService.getAllUser();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable("id") final Long id) {
        Optional<User> user = userService.getUser(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") final Long id) {
        userService.deleteUser(id);
    }

    @PutMapping
    public User updateUser(@RequestBody User u){
        Optional<User> project = userService.getUser(u.getId());
        return userService.saveUser(u);
    }

    @PostMapping()
    public User createUser(@RequestBody User u){
        return userService.saveUser(u);
    }

}
