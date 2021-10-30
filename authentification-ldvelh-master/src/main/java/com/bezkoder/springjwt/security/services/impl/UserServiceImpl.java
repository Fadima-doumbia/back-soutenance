package com.bezkoder.springjwt.security.services.impl;

import com.bezkoder.springjwt.dto.UserDto;
import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.repository.UserRepository;
import com.bezkoder.springjwt.security.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    private final ModelMapper modelMapper = new ModelMapper();


    @Override
    public Iterable<User> getAllUser() {
        return userRepository.findAll();
    }


    @Override
    public Optional<User> getUser(final Long id) {
        return userRepository.findById(id);
    }


    @Override
    public User saveUser(User u) {
        User save = userRepository.save(u);
        return save;
    }

    @Override
    public Optional<User> updateUser(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User updateUser(UserDto userDto) {
        return null;
    }

    public User updateUserDto(Long id, String username){//nouvelle que je test
        User currentUser = userRepository.getById(id);
        currentUser.setUsername(username);
        return userRepository.save(currentUser);
    }

/*
    public void updateCustomerContacts(long id, String username) {
        User user = repo.findById(id);
        user.username = username;
        repo.save(user);
    }
    }*/

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User deleteProject(Long id, String username) {
        return null;
    }

}