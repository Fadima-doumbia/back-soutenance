package com.bezkoder.springjwt.security.services;

import com.bezkoder.springjwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class UsersService {

    @Autowired
    private UserRepository userRepository;

    public void deleteUser(final Long id){
        userRepository.deleteById(id);
    }
}
