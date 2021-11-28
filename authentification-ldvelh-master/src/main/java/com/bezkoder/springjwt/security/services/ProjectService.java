package com.bezkoder.springjwt.security.services;

import com.bezkoder.springjwt.dto.ProjectDto;
import com.bezkoder.springjwt.models.ERole;
import com.bezkoder.springjwt.models.Project;
import com.bezkoder.springjwt.models.Role;
import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.payload.request.SearchProjectRequest;
import com.bezkoder.springjwt.repository.ProjectRepository;
import com.bezkoder.springjwt.repository.RoleRepository;
import com.bezkoder.springjwt.repository.UserRepository;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Data
@Service
public class ProjectService {
    @Autowired //permet d'avoir acces au repository'
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

    public User deleteProject(Long id, String username){
        Optional<User> userOptional = userRepository.findByUsername(username);
        User user = null;
        if (userOptional.isPresent()){
            Optional<Project> projetOptional = projectRepository.findById(id);
            user = userOptional.get();
            user.getProjects();

            user.getProjects().remove(projetOptional.get());
            return userRepository.save(user);
        }
        return user;
    }

        /* test delete par propriete
    public User deleteProject(Long id, String username){
        Optional<User> userOptional = userRepository.findByUsername(username);
        User user = null;
        if (userOptional.isPresent()){
            Optional<Project> projetOptional = projectRepository.findById(id);
            user = userOptional.get();
            user.getProjects();
            if (user.getProjects().contains(projetOptional)){
                user.getProjects().remove(projetOptional.get());
                return userRepository.save(user);
            }

        }
        return user;
    }*/


    public User deleteProjectEntren(Long id, String username){
        Optional<User> userOptional = userRepository.findByUsername(username);
        User user = null;
        Optional<Role> entreRole = roleRepository.findByName(ERole.ROLE_ENTREPRENEUR);
        if (userOptional.isPresent() && entreRole.isPresent()) {
            Optional<Project> projetOptional = projectRepository.findById(id);
            user = userOptional.get();
            user.getProjects().remove(projetOptional.get());
            // REMOVE FROM PARENT
            user.getProjects().remove(0);
            userRepository.save(user);
        }
        return user;
    }

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

    public Project updateProject(ProjectDto projectDto){
        Project project = modelMapper.map(projectDto, Project.class);
        return projectRepository.save(project);
    }

    /*    public Project save(Project project) {
        return projectRepository.save(project);
    }*/
   /* public Project saveProject(ProjectDto projectDto){
        User userOptional = userRepository.findById(projectDto.getUser().getId()).get();
        projectDto.setUser(userOptional);
        Project project = modelMapper.map(projectDto, Project.class);
        projectRepository.save(project);
        return project;
    }
*/
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
