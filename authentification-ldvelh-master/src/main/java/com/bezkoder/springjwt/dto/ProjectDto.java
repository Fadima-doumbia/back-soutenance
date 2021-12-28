package com.bezkoder.springjwt.dto;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ProjectDto {
    private Long id;
    private String name;
    private String description;
    private String besoin;
    private Date dateD;
    private Long userId;
}
