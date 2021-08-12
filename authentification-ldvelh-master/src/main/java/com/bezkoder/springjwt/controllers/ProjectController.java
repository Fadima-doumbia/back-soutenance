package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.dto.ProjectDto;
import com.bezkoder.springjwt.models.Project;
import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.security.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/auth/projets")
public class ProjectController {
    @Autowired
    private ProjectService projetService;

    @GetMapping
    public Iterable<Project> getProjects(){
        return projetService.getProjects();
    }

    @GetMapping("/{id}")
    public Project getProjet(@PathVariable("id") final Long id) {
        Optional<Project> projet = projetService.getProject(id);
        if (projet.isPresent()) {
            return projet.get();
        } else {
            return null;
        }
    }

    @PostMapping("/{idUser}")
    public User createProjet(@PathVariable("idUser") final Long idUser, @RequestBody ProjectDto projectDto){
        return projetService.saveProject(projectDto, idUser);
    }

    @DeleteMapping("/{id}")
    public void deleteProjet(@PathVariable("id") final Long id) {
        projetService.deleteProject(id);
    }

    @PutMapping("")
    public Project updateProjet(@RequestBody ProjectDto projectDto){
        return projetService.updateProject(projectDto);
    }
}
