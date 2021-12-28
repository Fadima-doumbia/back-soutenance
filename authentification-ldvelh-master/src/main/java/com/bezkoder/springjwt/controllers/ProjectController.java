package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.dto.ProjectDto;
import com.bezkoder.springjwt.dto.UserUpdateDto;
import com.bezkoder.springjwt.models.Project;
import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.payload.request.SearchProjectRequest;
import com.bezkoder.springjwt.repository.ProjectRepository;
import com.bezkoder.springjwt.repository.UserRepository;
import com.bezkoder.springjwt.security.services.ProjectService;
import com.bezkoder.springjwt.security.services.impl.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/projets")
public class ProjectController {
    @Autowired
    private ProjectService projetService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProjectRepository p;


        @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ENTREPRENEUR')")
        @PostMapping("/{idUser}")
        public User createProjet(@PathVariable("idUser") final Long idUser, @RequestBody ProjectDto projectDto){
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String username = auth.getName();
            Optional <User> optionalUser = userRepository.findByUsername(username);
            Long id = optionalUser.get().getId();
            return projetService.saveProject(projectDto, id);
        }

        @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ENTREPRENEUR', 'ROLE_INVESTISSEUR')")
        @GetMapping("")
        public Iterable<Project> getProjects(){
            return projetService.getProjects();
        }

        @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ENTREPRENEUR', 'ROLE_INVESTISSEUR')")
        @GetMapping("/{id}")
        public Project getProjet(@PathVariable("id") final Long id) {
            Optional<Project> projet = projetService.getProject(id);
            if (projet.isPresent()) {
                return projet.get();
            } else {
                return null;
            }
        }

        @PostMapping("/searchProject")
        public List<Project> searchProjectByName(@RequestBody SearchProjectRequest searchProjectRequest){
            List<Project> project = null;
            if (searchProjectRequest.getName().isEmpty()){
                project = projetService.getProjects();
            }else{
                project=projetService.searchProjectByName(searchProjectRequest);
            }
            return project;
        }

        @PreAuthorize("hasAnyRole('ROLE_ENTREPRENEUR', 'ROLE_ENTREPRENEUR')")
        @PutMapping("")
        public ResponseEntity<String> updateProjet(@RequestBody() ProjectDto projectDto, Authentication authentication) {
            final UserDetailsImpl user = (UserDetailsImpl) authentication.getPrincipal();
            Optional <User> userOptional = userRepository.findById(user.getId());

            if(projectDto.getUserId() == userOptional.get().getId()){
                userOptional.get().getProjects().remove(projectDto);
                projetService.updateProject(projectDto);
                return new ResponseEntity<String>("update ok",HttpStatus.OK);
            }else {
                return new ResponseEntity<String>("Ce Projet ne vous appartient pas; Vous ne pourrez pas le modifier", HttpStatus.FORBIDDEN);
            }
        }

        @PreAuthorize("hasAnyRole('ROLE_ENTREPRENEUR', 'ROLE_ENTREPRENEUR')")
        @DeleteMapping("/{id}")
        public ResponseEntity<String> deleteProjet(@PathVariable("id") final Long id, Authentication authentication) {
            final UserDetailsImpl user = (UserDetailsImpl) authentication.getPrincipal();
            Optional <User> userOptional = userRepository.findById(user.getId());
            Optional <Project> optionalProject = projetService.getProject(id);
            if(optionalProject.get().getUserId() == userOptional.get().getId()){
                userOptional.get().getProjects().remove(optionalProject);
                projetService.projectDelete(optionalProject.get().getId());
                return new ResponseEntity<String>("delete ok",HttpStatus.OK);
            }else {
                return new ResponseEntity<String>("Ce Projet ne vous appartient pas; Vous ne pourrez pas le supprimer", HttpStatus.FORBIDDEN);
            }
        }


        @PreAuthorize("hasRole('ADMIN')")
        @PutMapping("/admin")
        public Project updateUProject(@RequestBody ProjectDto projectDto){
            return projetService.adminUpdateProject(projectDto);
        }
        @PreAuthorize("hasRole('ADMIN')")
        @DeleteMapping("/admin/{id}")
        public void deleteProjetAdmin(@PathVariable("id") final Long id) {
            projetService.adminDeleteProject(id);
        }
//    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ENTREPRENEUR')")
    @PutMapping("")
    public Project updateProjet(@RequestBody ProjectDto projectDto){
        return projetService.updateProject(projectDto);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ENTREPRENEUR')")
    @DeleteMapping("/{id}")
    public void deleteProjet(@PathVariable("id") final Long id) {
        projetService.projectDelete(id);
    }


    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ENTREPRENEUR')")
    @DeleteMapping("/delete/{id}")
    public void deleteProject(@PathVariable("id") final Long id) {
        String authentication = Authentication.class.getName();
        Optional <User> optionalUser = userRepository.findByUsername(authentication);
        Long userId = optionalUser.get().getId();
        Optional <Project> projectOptional = projetService.getProject(id);
        Long projectUserId = projectOptional.get().getUserId();
        if(userId == projectUserId){
            projetService.projectDelete(id);
        }
    }
}
