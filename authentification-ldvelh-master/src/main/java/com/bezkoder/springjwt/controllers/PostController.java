package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.dto.PostDto;
import com.bezkoder.springjwt.models.Post;
import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.payload.request.SearchRequest;
import com.bezkoder.springjwt.repository.UserRepository;
import com.bezkoder.springjwt.security.services.PostService;
import com.bezkoder.springjwt.security.services.impl.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/posts")
public class PostController {
    @Autowired
    PostService postService;
    @Autowired
    UserRepository userRepository;


    @PostMapping("/{idUser}")
    public User createPost(@PathVariable("idUser") final Long idUser, @RequestBody PostDto postDto){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Optional<User> optionalUser = userRepository.findByUsername(username);
        Long id = optionalUser.get().getId();
        return postService.savePost(postDto, id);
    }

    @GetMapping("")
    public Iterable<Post> getPosts(){
        return postService.getPosts();
    }

    @GetMapping("/{id}")
    public Post getPost(@PathVariable("id") final Long id) {
        Optional<Post> post = postService.getPost(id);
        if (post.isPresent()) {
            return post.get();
        } else {
            return null;
        }
    }

    @PostMapping("/searchProject")
    public List<Post> searchPostByName(@RequestBody SearchRequest searchRequest){
        List<Post> posts = null;
        if (searchRequest.getName().isEmpty()){
            posts = postService.getPosts();
        }else{
            posts=postService.searchPostByText(searchRequest);
        }
        return posts;
    }

    @PutMapping("")
    public ResponseEntity<String> updatePost(@RequestBody() PostDto postDto, Authentication authentication) {
        final UserDetailsImpl user = (UserDetailsImpl) authentication.getPrincipal();
        Optional <User> userOptional = userRepository.findById(user.getId());

        if(postDto.getUserId() == userOptional.get().getId()){
            postService.updatePost(postDto);
            return new ResponseEntity<String>("update ok", HttpStatus.OK);
        }else {
            return new ResponseEntity<String>("Ce Projet ne vous appartient pas; Vous ne pourrez pas le modifier", HttpStatus.FORBIDDEN);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProjet(@PathVariable("id") final Long id, Authentication authentication) {
        final UserDetailsImpl user = (UserDetailsImpl) authentication.getPrincipal();
        Optional <User> userOptional = userRepository.findById(user.getId());
        Optional <Post> optionalPost = postService.getPost(id);
        if(optionalPost.get().getUserId() == userOptional.get().getId()){
//            userOptional.get().getPosts().remove(optionalPost);
            postService.postDelete(optionalPost.get().getId());
            return new ResponseEntity<String>("delete ok",HttpStatus.OK);
        }else {
            return new ResponseEntity<String>("Ce Projet ne vous appartient pas; Vous ne pourrez pas le supprimer", HttpStatus.FORBIDDEN);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/admin")
    public Post updateUPost(@RequestBody PostDto postDto){
        return postService.adminUpdatePost(postDto);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/admin/{id}")
    public void deletePostAdmin(@PathVariable("id") final Long id) {
        postService.adminDeletePost(id);
    }
}
