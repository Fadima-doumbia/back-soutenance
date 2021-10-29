package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.dto.UserDto;
import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.security.services.UserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Data
@RestController
@RequestMapping("api/users")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserService usersService;

    @PostMapping()
    public User createUser(@RequestBody User u){
        return userService.saveUser(u);
    }


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

//    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATEUR', 'ROLE_USER')")
    @PutMapping
    public User updateUser(@RequestBody User u){
        Optional<User> user = userService.getUser(u.getId());
        return userService.saveUser(u);
    }

    //nouvelle que je test
    @PutMapping("/dto")
    public User updateUserDto(@RequestBody UserDto userDto){
        Optional<User> user = userService.getUser(userDto.getId());
        return userService.updateUser(userDto);
    }


    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
     @DeleteMapping("/delete/{id}")
     public void deleteUser(@PathVariable("id") Long id) {
         usersService.deleteUser(id);
     }
}
