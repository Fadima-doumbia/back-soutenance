package com.bezkoder.springjwt.security.services.impl;

import com.bezkoder.springjwt.dto.ProjectDto;
import com.bezkoder.springjwt.dto.UserDto;
import com.bezkoder.springjwt.models.Project;
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

    //**********************************methode de recuperation de tout les objets **************************************************************

    @Override
    public Iterable<User> getAllUser() {
        return userRepository.findAll();
    }

    //**********************************methode de recuperation d'un objet **************************************************************

    @Override
    public Optional<User> getUser(final Long id) {
        return userRepository.findById(id);
    }

    //**********************************methode d'enreigistrement **************************************************************

    @Override
    public User saveUser(User u) {
        return userRepository.save(u);
    }

    //**********************************methode de modififation **************************************************************
    public User updateUser(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        return userRepository.save(user);
    }

    //**********************************methode de suppression **************************************************************

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User deleteProject(Long id, String username) {
        return null;
    }

}