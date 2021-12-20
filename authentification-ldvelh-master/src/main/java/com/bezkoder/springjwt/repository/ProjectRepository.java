package com.bezkoder.springjwt.repository;

import com.bezkoder.springjwt.dto.UserDto;
import com.bezkoder.springjwt.models.ERole;
import com.bezkoder.springjwt.models.Project;
import com.bezkoder.springjwt.models.Role;
import com.bezkoder.springjwt.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Optional;

@Repository
@CrossOrigin("*")//intervient quand je viens du front il lie le front et back
public interface ProjectRepository extends CrudRepository<Project,Long> {
    List<Project> findByName(String name);
    List<Project> findAll();

}
