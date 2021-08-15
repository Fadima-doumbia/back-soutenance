package com.bezkoder.springjwt.security.services.impl;

import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.repository.UserRepository;
import com.bezkoder.springjwt.security.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    //**********************************methode de recuperation de tout les objets **************************************************************

    @Override
    public Iterable<User> getAllUser(){
        return userRepository.findAll();
    }

    //**********************************methode de recuperation d'un objet **************************************************************

    @Override
    public Optional<User> getUser(final Long id){
        return userRepository.findById(id);
    }

    //**********************************methode de suppression **************************************************************

    @Override
    public void deleteUser(final Long id){
        userRepository.deleteById(id);
    }
    //**********************************methode d'enreigistrement **************************************************************

    @Override
    public User saveUser(User u){
        User save = userRepository.save(u);
        return save;
    }

    //**********************************methode de modififation **************************************************************
    @Override
    public Optional<User> updateUser(Long id){
        return userRepository.findById(id);
    }
//**********************************************************************************************************************
//        public Project saveProject(ProjectDto bookDto) {
//        var user = userRepository.findById(bookDto.getUser_id());
//        var project = ProjectDto.convertToProject(bookDto);
//        ProjectRepository.save(project);
//        user.get();
//        userRepository.save(user.get());
//        return project;
//    }
//
//
//    public User userAddChapter(Long id, Project project, User u) {
//        Optional<User> userOptional = userRepository.findById(id);
//        User user = null;
//        if (userOptional.isPresent()) {
//            user = userOptional.get();
//            user.getProject().add(project);
//            ProjectRepository.save(project);
//            userRepository.save(user);
//        }
//        return user;
//    }

//    public User userDeleteChapter(int id, int chapterId) {
//        Optional<User> userOptional = userRepository.findById(id);
//        Optional<Project> chapterOptional = ProjectRepository.findById(chapterId);
//        User user = null;
//        Project project = null;
//        if (userOptional.isPresent() && chapterOptional.isPresent()) {
//            user = userOptional.get();
//            project = chapterOptional.get();
//            user.getProject().remove(project);
//            ProjectRepository.delete(project);
//            userRepository.save(user);
//        }
//        return user;
//    }
}
