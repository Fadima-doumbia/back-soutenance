package com.bezkoder.springjwt.repository;


import com.bezkoder.springjwt.models.Project;
import com.bezkoder.springjwt.models.Role;
import com.bezkoder.springjwt.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.ResponseEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Repository
@CrossOrigin("*")
public interface ProjectRepository extends JpaRepository<Project,Long> {
    List<Project> findByName(String name);
    List<Project> findAll();

}
