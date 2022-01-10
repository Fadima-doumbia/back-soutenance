package com.bezkoder.springjwt.repository;

import com.bezkoder.springjwt.models.Category;
import com.bezkoder.springjwt.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByName(String name);

}
