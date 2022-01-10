package com.bezkoder.springjwt.security.services;


import com.bezkoder.springjwt.models.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    List<Category> getAllCategories();
    List<Category> getCategoryByName(String category);
    Optional<Category> getCategoryById(Long id);

}
