package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.dto.ProjectDto;
import com.bezkoder.springjwt.models.ERole;
import com.bezkoder.springjwt.models.Project;
import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.payload.request.SearchProjectRequest;
import com.bezkoder.springjwt.security.services.ProjectService;
import com.bezkoder.springjwt.security.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/projets")
public class ProjectController {
    @Autowired
    private ProjectService projetService;
    private UserService userService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ENTREPRENEUR')")//authoriser avec des roles
    @PostMapping("/{idUser}")
    public User createProjet(@PathVariable("idUser") final Long idUser, @RequestBody ProjectDto projectDto){
        return projetService.saveProject(projectDto, idUser);
    }
    /*public Project createProjet( @RequestBody ProjectDto projectDto){
        return projetService.saveProject(projectDto);}*/

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



    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ENTREPRENEUR')")
    @PutMapping("")
    public Project updateProjet(@RequestBody ProjectDto projectDto){
        return projetService.updateProject(projectDto);
    }


    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ENTREPRENEUR')")
    @DeleteMapping("/{id}")
    public void deleteProjet(@PathVariable("id") final Long id, Authentication authentication) {
        projetService.deleteProject(id, authentication.getName());

    }


    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ENTREPRENEUR')")
    @DeleteMapping("/admin/{id}")
    public void deleteAdminProjet(@PathVariable("id") final Long id, Authentication authentication, ERole role) {
        projetService.deleteProjectAdmin(id, authentication.getName());
    }

}
