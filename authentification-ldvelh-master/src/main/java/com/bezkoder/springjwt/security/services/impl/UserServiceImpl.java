package com.bezkoder.springjwt.security.services.impl;

import com.bezkoder.springjwt.dto.ProjectDto;
import com.bezkoder.springjwt.models.Project;
import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.repository.UserRepository;
import com.bezkoder.springjwt.security.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

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
        User save = userRepository.save(u);
        return save;
    }

    //**********************************methode de modififation **************************************************************
    @Override
    public Optional<User> updateUser(Long id) {
        return userRepository.findById(id);
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
    //    public void deleteUser(final Long id){

//    @Override
//    public User deleteProject(Long id, String username) {
//        return null;
//    }
//        userRepository.deleteById(id);
//    }
//    public void deleteUser(final Long id){
//        userRepository.deleteById(id);
//    }

//    @Override
//    public void deleteProjectUserId(Long id, String username){
//        Optional<User> userOptional = userRepository.findById(userId);
//        Set<Project> projectList = null;
//        User user = null;
//        if(userOptional.isPresent()){
//            user = userOptional.get();
//            projectList = user.getProjects();
//            for (Project project:
//                    projectList) {
//                projectList.remove(id);
//            }
//            userRepository.save(user);
//        }
//    }
