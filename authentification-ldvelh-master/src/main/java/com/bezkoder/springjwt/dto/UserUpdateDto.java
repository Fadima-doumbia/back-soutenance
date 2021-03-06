package com.bezkoder.springjwt.dto;

import com.bezkoder.springjwt.models.Project;
import com.bezkoder.springjwt.models.Role;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class UserUpdateDto {
    private Long id;
    private String username;
    private String email;
    private String presentation;
    private String password;
    private Set<Role> roles = new HashSet<>();
    private Set<Project> projects = new HashSet<>();
}
