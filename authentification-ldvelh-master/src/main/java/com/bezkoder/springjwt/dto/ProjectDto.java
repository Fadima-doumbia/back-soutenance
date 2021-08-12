package com.bezkoder.springjwt.dto;

import com.bezkoder.springjwt.models.Project;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ProjectDto {
    private Long id;
    private String name;
    private String description;
    private String entrepreneur;
    private String besoin;
    private Date dateD;
    private Long userId;

//    public static  Project convertToProject( ProjectDto projectDto) {
//        return new  Project(projectDto.userId, projectDto.name, projectDto.description,
//                projectDto.entrepreneur, projectDto.besoin, projectDto.dateD);
//    }
}
