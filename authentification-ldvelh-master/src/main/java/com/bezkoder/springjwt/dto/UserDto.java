package com.bezkoder.springjwt.dto;

import com.bezkoder.springjwt.models.Project;
import com.bezkoder.springjwt.models.Role;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Data
public class UserDto {

    private Long id;
    private String username;
    private String email;
    private String presentation;
    private String password;
    private Set<Role> roles = new HashSet<>();
    private Set<Project> projects = new HashSet<>();
<<<<<<< HEAD
=======

>>>>>>> 4549242f7ac23659af0393b1565390736a207f35
}

