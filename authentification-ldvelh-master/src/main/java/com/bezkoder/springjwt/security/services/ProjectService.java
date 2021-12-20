package com.bezkoder.springjwt.security.services;

import com.bezkoder.springjwt.dto.ProjectDto;
import com.bezkoder.springjwt.models.Project;
import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.payload.request.SearchProjectRequest;
import com.bezkoder.springjwt.repository.ProjectRepository;
import com.bezkoder.springjwt.repository.RoleRepository;
import com.bezkoder.springjwt.repository.UserRepository;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Data
@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;
    private final ModelMapper modelMapper = new ModelMapper();
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;

    public Optional<Project> getProject(final Long id){
        return projectRepository.findById(id);
    }

    public List<Project> getProjects(){
        return projectRepository.findAll();
    }

    public List<Project> searchProjectByName (SearchProjectRequest searchProjectRequest){
        return projectRepository.findByName(searchProjectRequest.getName());
    }

    public void projectDelete(Long id){
        Project projetOptional = projectRepository.findById(id).get();
        projectRepository.delete(projetOptional);
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
            user.getProjects().add(project);
            return userRepository.save(user);
        }
        return user;
    }

}
