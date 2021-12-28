package com.bezkoder.springjwt.repository;


import com.bezkoder.springjwt.models.Project;
<<<<<<< HEAD
import com.bezkoder.springjwt.models.Role;
import com.bezkoder.springjwt.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.ResponseEntity;
=======

import org.springframework.data.jpa.repository.JpaRepository;
>>>>>>> 11e01a14f61a4b586f0a56fe9ff8a0a8cec407f1
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Repository
@CrossOrigin("*")
public interface ProjectRepository extends JpaRepository<Project,Long> {
    List<Project> findByName(String name);
    List<Project> findAll();

}
