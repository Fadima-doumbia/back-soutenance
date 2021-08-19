package com.bezkoder.springjwt.security.services;

import com.bezkoder.springjwt.models.User;

import java.util.Optional;

public interface UserService {
    Iterable<User> getAllUser();

    Optional<User> getUser(Long id);

    User saveUser(User u);

    Optional<User> updateUser(Long id);

    void deleteUser(Long id);
    void deleteProjectUserId(Long id, Long userId);

    //    public Iterable<User> getUsers(){
    //        return userRepository.findAll();
    //    }
}
