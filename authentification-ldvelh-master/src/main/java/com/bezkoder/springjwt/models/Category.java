package com.bezkoder.springjwt.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table
@Data
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;
}
