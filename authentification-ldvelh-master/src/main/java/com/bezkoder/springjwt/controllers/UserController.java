package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.dto.UserDto;
import com.bezkoder.springjwt.models.ERole;
import com.bezkoder.springjwt.models.Role;
import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.payload.request.SignupRequest;
import com.bezkoder.springjwt.payload.response.MessageResponse;
import com.bezkoder.springjwt.repository.RoleRepository;
import com.bezkoder.springjwt.repository.UserRepository;
import com.bezkoder.springjwt.security.jwt.JwtUtils;
import com.bezkoder.springjwt.security.services.UserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Data
@RestController
@RequestMapping("api/users")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserService usersService;
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping()
    public User createUser(@RequestBody User u){
        return userService.saveUser(u);
    }

@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping("/createAdmin")
    public ResponseEntity<?> registerUser(@RequestBody SignupRequest signUpRequest, Authentication authentication) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                signUpRequest.getPresentation(),
                encoder.encode(signUpRequest.getPassword()));
        Set<String> strRoles = new HashSet<String>(Collections.singletonList(signUpRequest.getRole()));
        Set<Role> roles = new HashSet<>();
        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        roles.add(adminRole);
        user.setRoles(roles);

        userService.createNewAdmin(user, authentication.getName());

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    /*    @DeleteMapping("/{id}")
    public void deleteProjet(@PathVariable("id") final Long id, Authentication authentication) {
        projetService.deleteProject(id, authentication.getName());

    }*/

    @GetMapping("")
    public Iterable<User> listUser(){
        return userService.getAllUser();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable("id") final Long id) {
        Optional<User> user = userService.getUser(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            return null;
        }
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATEUR', 'ROLE_USER')")
    @PutMapping
    public User updateUser(@RequestBody User u){
        Optional<User> user = userService.getUser(u.getId());
        return userService.saveUser(u);
    }
    //nouvelle que je test
    @PutMapping("/dto")
    public User updateUserDto(@RequestBody UserDto userDto){
        Optional<User> user = userService.getUser(userDto.getId());
        return userService.updateUserDto(userDto);
    }
    //nouvelle que je test
    @PutMapping("/update")
    public User updateUserUsername(Authentication authentication, @RequestBody User user){
        return userService.updateUser(authentication.getName(), user);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
     @DeleteMapping("/delete/{id}")
     public void deleteUser(@PathVariable("id") Long id) {
         usersService.deleteUser(id);
     }
}
