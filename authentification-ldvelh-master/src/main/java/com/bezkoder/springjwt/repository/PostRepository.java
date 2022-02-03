package com.bezkoder.springjwt.repository;

import com.bezkoder.springjwt.models.Post;
import com.bezkoder.springjwt.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Optional;

@Repository
@CrossOrigin("*")
public interface PostRepository extends JpaRepository<Post,Long> {
    List<Post> findAll();
    Optional<Post> findById(Long id);
    List<Post> findByTexte(String texte);
    List<Post> findAllByOrderByIdDesc();

}
