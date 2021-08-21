package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.security.services.UserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@Data
@RestController
@RequestMapping("api/admin")
public class AdminController {

    @Autowired
    UserService userService;
    //**************************** ajouter des projet******************************************************

    @PostMapping("/create")
    public User createUser(@RequestBody User u){
        return userService.saveUser(u);
    }

    //**************************** recuperer des projets******************************************************

    @GetMapping("")
    public Iterable<User> listUser(){
        return userService.getAllUser();
    }

    //**************************** recuperer un projet******************************************************

    @GetMapping("/{id}")
    public User getUser(@PathVariable("id") final Long id) {
        Optional<User> user = userService.getUser(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            return null;
        }
    }

    //**************************** modifier des projet******************************************************
    // @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATEUR', 'ROLE_USER')")
    @PutMapping
    public User updateUser(@RequestBody User u){
        Optional<User> project = userService.getUser(u.getId());
        return userService.saveUser(u);
    }


    //**************************** supprimer des projet******************************************************
    //    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
    }
}
