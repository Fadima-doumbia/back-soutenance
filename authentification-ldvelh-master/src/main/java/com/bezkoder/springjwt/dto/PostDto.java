package com.bezkoder.springjwt.dto;

import lombok.Data;

@Data
public class PostDto {
    private Long id;
    private String texte;
    private String image;
    private boolean isLike;
    private Long userId;
}
