package com.bezkoder.springjwt.repository;

import com.bezkoder.springjwt.models.Project;
import com.bezkoder.springjwt.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Optional;

@Repository
@CrossOrigin("*")//intervient quand je viens du front il lie le front et back
public interface ProjectRepository extends CrudRepository<Project,Long> {
    static void save(Optional<User> user) {
    }
}
