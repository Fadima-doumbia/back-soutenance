package com.bezkoder.springjwt.security.services;

import com.bezkoder.springjwt.dto.ProjectDto;
import com.bezkoder.springjwt.models.Project;
import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.repository.ProjectRepository;
import com.bezkoder.springjwt.repository.UserRepository;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Data
@Service
public class ProjectService {
    //permet d'avoir acces au repository'
    @Autowired
    private ProjectRepository projectRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private UserRepository userRepository;

    public Optional<Project> getProject(final Long id){
        return projectRepository.findById(id);
    }

    public Project save(Project project) {
        return projectRepository.save(project);
    }

    public Iterable<Project> getProjects(){
        return projectRepository.findAll();
    }

    public void deleteProject(final Long id){
        projectRepository.deleteById(id);
    }

    public Project updateProject(ProjectDto projectDto){
        Project project = modelMapper.map(projectDto, Project.class);
            return projectRepository.save(project);
    }
    public User saveProject(ProjectDto projectDto, Long idUser){
        Optional<User> userOptional = userRepository.findById(idUser);
        Project project = modelMapper.map(projectDto, Project.class);
        User user = null;
        if (userOptional.isPresent()){
            user = userOptional.get();
            user.getProject().add(project);
            return userRepository.save(user);
        }
        return user;
    }
}
