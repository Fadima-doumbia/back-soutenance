package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.dto.ProjectDto;
import com.bezkoder.springjwt.models.Project;
import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.security.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/projets")
public class ProjectController {
    @Autowired
    private ProjectService projetService;

    //**************************** recuperer des projets ******************************************************
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ENTREPRENEUR', 'ROLE_INVESTISSEUR')")
    @GetMapping
    public Iterable<Project> getProjects(){
        return projetService.getProjects();
    }

    //**************************** recuperer un projet******************************************************
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

    //**************************** ajouter un objet ******************************************************
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ENTREPRENEUR')")//authoriser avec des roles
    @PostMapping("/{idUser}")
    public User createProjet(@PathVariable("idUser") final Long idUser, @RequestBody ProjectDto projectDto){
        return projetService.saveProject(projectDto, idUser);
    }

    //**************************** supprimer un objet ******************************************************
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ENTREPRENEUR')")
    @DeleteMapping("/{id}")
    public void deleteProjet(@PathVariable("id") final Long id) {
        projetService.deleteProject(id);
    }

    //**************************** modifier un objet ******************************************************
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ENTREPRENEUR')")
    @PutMapping("")
    public Project updateProjet(@RequestBody ProjectDto projectDto){
        return projetService.updateProject(projectDto);
    }
}
