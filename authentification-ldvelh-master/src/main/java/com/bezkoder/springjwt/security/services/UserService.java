package com.bezkoder.springjwt.security.services;

import com.bezkoder.springjwt.dto.ProjectDto;
import com.bezkoder.springjwt.dto.UserDto;
import com.bezkoder.springjwt.models.User;

import java.util.Optional;

public interface UserService {
    Iterable<User> getAllUser();

    Optional<User> getUser(Long id);

    User saveUser(User u);

//    User updateUser(User user);
//    User updateUser(UserDto userDto);
    Optional<User> updateUser(Long id);
    User updateUser(UserDto userDto);//nouvelle que je test

    void deleteUser(Long id);

    User deleteProject(Long id, String username);
}
