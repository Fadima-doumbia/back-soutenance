package com.bezkoder.springjwt.security.services.impl;

import com.bezkoder.springjwt.dto.UserDto;
import com.bezkoder.springjwt.models.ERole;
import com.bezkoder.springjwt.models.Role;
import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.repository.RoleRepository;
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
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Iterable<User> getAllUser() {
        return userRepository.findAll();
    }


    @Override
    public Optional<User> getUser(final Long id) {
        return userRepository.findById(id);
    }

    public User getUserById(Long id) {
        return null;
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

    public  User createNewAdmin(User user, String username){
        Optional<User> userOptional = userRepository.findByUsername(username);
        User newUser = null;
        Optional<Role> roleAdmin = roleRepository.findByName(ERole.ROLE_ADMIN);
        if(userOptional.isPresent() && roleAdmin.isPresent()){
            newUser = userRepository.save(user);
        }
        return newUser;
    }

/*
    public User deleteProjectAdmin(Long id, String username){
        Optional<User> userOptional = userRepository.findByUsername(username);
        User user = null;
        Optional<Role> adminRole = roleRepository.findByName(ERole.ROLE_ADMIN);
        if (userOptional.isPresent() && adminRole.isPresent()){
            Optional<Project> projetOptional = projectRepository.findById(id);
            user = userOptional.get();
            user.getProjects().remove(projetOptional.get());
            return userRepository.save(user);
        }
        return user;
    }
* */

/*    public User saveProject(ProjectDto projectDto, Long idUser){
        Optional<User> userOptional = userRepository.findById(idUser);
        Project project = modelMapper.map(projectDto, Project.class);
        User user = null;
        if (userOptional.isPresent()){
            user = userOptional.get();
            user.getProjects().add(project);
            return userRepository.save(user);
        }
        return user;
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