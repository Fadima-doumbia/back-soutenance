package com.bezkoder.springjwt.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
        private Long id;
        private String username;
        private String email;
        private String presentation;
        private String password;
}
