package com.bezkoder.springjwt.security.services;

import com.bezkoder.springjwt.dto.UserUpdateDto;
import com.bezkoder.springjwt.models.User;

import java.util.Optional;

public interface UserService {
    Iterable<User> getAllUser();

    Optional<User> getUser(Long id);

    User saveUser(User u);

    User createNewAdmin(User user, String username);

//    User updateUser(UserDto userDto);
    User updateUser(UserUpdateDto userUpdateDto);

    void deleteUser(Long id);

}
