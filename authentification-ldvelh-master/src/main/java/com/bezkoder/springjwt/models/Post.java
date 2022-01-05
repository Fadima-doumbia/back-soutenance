package com.bezkoder.springjwt.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@NoArgsConstructor
@Data
@Entity
@Table(	name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String texte;
    private String image;
    private boolean isLike;
    private Long userId;
}
